package ru.s4nchez.randomcats.presentation.presenter.main

import io.reactivex.android.schedulers.AndroidSchedulers
import ru.s4nchez.randomcats.domain.CatsInteractor
import ru.s4nchez.randomcats.presentation.presenter.BasePresenter
import ru.s4nchez.randomcats.presentation.view.main.MainView

class MainPresenter(
    private val catsInteractor: CatsInteractor
) : BasePresenter<MainView>() {

    override fun removeView() {
        super.removeView()
        catsInteractor.closeApp().subscribe()
    }

    fun loadCat() {
        view?.hidePhoto()
        view?.showProgress()
        view?.hideUpdateButton()

        val d = catsInteractor.getCat()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showCat(it.url)
            }, {
                view?.hideProgress()
                view?.showUpdateButton()
                view?.showCommonError()
            })
        disposable.add(d)
    }
}