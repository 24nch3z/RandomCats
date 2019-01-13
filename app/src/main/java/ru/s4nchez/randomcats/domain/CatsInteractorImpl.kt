package ru.s4nchez.randomcats.domain

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.randomcats.data.cat.model.Cat
import ru.s4nchez.randomcats.data.cat.repository.CatsRepository

class CatsInteractorImpl(
    private val catsRepository: CatsRepository
) : CatsInteractor {

    override fun getCat(): Single<Cat> {
        return catsRepository.getCat()
            .subscribeOn(Schedulers.io())
    }
}