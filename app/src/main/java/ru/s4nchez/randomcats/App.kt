package ru.s4nchez.randomcats

import android.app.Application
import ru.s4nchez.randomcats.di.AppComponent
import ru.s4nchez.randomcats.di.AppModule
import ru.s4nchez.randomcats.di.DaggerAppComponent
import ru.s4nchez.randomcats.di.NetModule

class App : Application() {

    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        dagger = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule())
            .build()
    }
}