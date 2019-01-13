package ru.s4nchez.randomcats.data.cat.repository

import io.reactivex.Single
import ru.s4nchez.randomcats.data.cat.model.Cat

interface CatsRepository {
    fun getCat(): Single<Cat>
}