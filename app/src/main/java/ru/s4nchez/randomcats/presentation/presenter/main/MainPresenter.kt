package ru.s4nchez.randomcats.presentation.presenter.main

import io.reactivex.android.schedulers.AndroidSchedulers
import ru.s4nchez.randomcats.domain.CatsInteractor
import ru.s4nchez.randomcats.presentation.presenter.BasePresenter
import ru.s4nchez.randomcats.presentation.view.main.MainView

class MainPresenter(
    private val catsInteractor: CatsInteractor
) : BasePresenter<MainView>() {

    fun loadCat() {
        view?.hidePhoto()
        view?.showProgress()
        val d = catsInteractor.getCat()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showPhoto()
                view?.showCat(it.url)
            }, {})
        disposable.add(d)
    }
}