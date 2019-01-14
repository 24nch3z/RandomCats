package ru.s4nchez.randomcats.data.cat.repository

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.randomcats.data.cat.APIInterface
import ru.s4nchez.randomcats.data.cat.model.Cat
import java.util.*

class CatsRepositoryImpl(
    private val apiInterface: APIInterface
) : CatsRepository {

    private val minimumCacheSize = 10
    private val maximumCacheSize = 20

    private val cats: ArrayDeque<Cat> = ArrayDeque()
    private var catDisposable: Disposable? = null
    private var loadingNewCatsPacket = false

    init {
        cats.add(Cat("http://www.kartinkijane.ru/pic/201504/640x480/kartinkijane.ru-84993.jpg"))
    }

    override fun getCat(): Single<Cat> {
        return Single.create<Cat> { it.onSuccess(cats.poll()) }
            .doOnEvent { t1, t2 -> loadNewCatsPacket() }
            .onErrorResumeNext { apiInterface.meow() }
    }

    override fun closeApp(): Completable {
        return Completable.create {
            loadingNewCatsPacket = false
            catDisposable?.dispose()
            catDisposable = null
            it.onComplete()
        }
    }

    private fun loadNewCatsPacket() {
        if (!loadingNewCatsPacket && cats.size < minimumCacheSize) {
            Log.d("sssss", "Необходима свежая порция котиков")
            loadingNewCatsPacket = true
            loadNextCat()
        }
    }

    private fun loadNextCat() {
        Log.d("sssss", "Новый котик загружается")
        catDisposable?.dispose()
        catDisposable = null
        catDisposable = apiInterface.meow()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                cats.addLast(it)
                if (cats.size < maximumCacheSize) {
                    loadNextCat()
                } else {
                    Log.d("sssss", "Все котики загружены, загрузка новых временно приостановлена")
                    loadingNewCatsPacket = false
                }
            }, {})
    }
}