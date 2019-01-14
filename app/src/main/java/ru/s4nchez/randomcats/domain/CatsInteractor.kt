package ru.s4nchez.randomcats.domain

import io.reactivex.Completable
import io.reactivex.Single
import ru.s4nchez.randomcats.data.cat.model.Cat

interface CatsInteractor {
    fun getCat(): Single<Cat>
    fun closeApp(): Completable
}