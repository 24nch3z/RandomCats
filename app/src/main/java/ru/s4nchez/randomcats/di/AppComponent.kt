package ru.s4nchez.randomcats.di

import dagger.Component
import ru.s4nchez.randomcats.presentation.view.main.MainActivity
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetModule::class, CatModule::class])
@Singleton
interface AppComponent {
    fun inject(view: MainActivity)
}