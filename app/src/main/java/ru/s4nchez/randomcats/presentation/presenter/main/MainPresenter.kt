package ru.s4nchez.randomcats.presentation.presenter.main

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.randomcats.domain.CatsInteractor
import ru.s4nchez.randomcats.presentation.presenter.BasePresenter
import ru.s4nchez.randomcats.presentation.view.main.MainView
import java.util.concurrent.TimeUnit

class MainPresenter(
    private val catsInteractor: CatsInteractor
) : BasePresenter<MainView>() {

    override fun removeView() {
        super.removeView()
        catsInteractor.closeApp().subscribe()
    }

    fun loadCatWithTimer() {
        val d = Completable.timer(5L, TimeUnit.SECONDS)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe { loadCat() }
        disposable.add(d)
    }

    fun loadCat() {
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