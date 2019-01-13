package ru.s4nchez.randomcats.data.cat

import io.reactivex.Single
import retrofit2.http.GET
import ru.s4nchez.randomcats.data.cat.model.Cat

const val BASE_URL = "https://aws.random.cat/"

interface APIInterface {

    @GET("meow")
    fun meow(): Single<Cat>
}