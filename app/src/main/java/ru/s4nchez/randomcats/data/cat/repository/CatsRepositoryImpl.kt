package ru.s4nchez.randomcats.data.cat.repository

import io.reactivex.Single
import ru.s4nchez.randomcats.data.cat.APIInterface
import ru.s4nchez.randomcats.data.cat.model.Cat

class CatsRepositoryImpl(
    private val apiInterface: APIInterface
) : CatsRepository {

    override fun getCat(): Single<Cat> {
        return apiInterface.meow()
    }
}