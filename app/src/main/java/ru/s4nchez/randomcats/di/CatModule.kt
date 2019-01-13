package ru.s4nchez.randomcats.di

import dagger.Module
import dagger.Provides
import ru.s4nchez.randomcats.data.cat.APIInterface
import ru.s4nchez.randomcats.data.cat.repository.CatsRepository
import ru.s4nchez.randomcats.data.cat.repository.CatsRepositoryImpl
import ru.s4nchez.randomcats.domain.CatsInteractor
import ru.s4nchez.randomcats.domain.CatsInteractorImpl
import ru.s4nchez.randomcats.presentation.presenter.main.MainPresenter
import javax.inject.Singleton

@Module
class CatModule {

    @Provides
    @Singleton
    fun provideCatsRepository(apiInterface: APIInterface): CatsRepository {
        return CatsRepositoryImpl(apiInterface)
    }

    @Provides
    @Singleton
    fun provideCatsInteractor(catsRepository: CatsRepository): CatsInteractor {
        return CatsInteractorImpl(catsRepository)
    }

    @Provides
    fun provideMainPresenter(catsInteractor: CatsInteractor): MainPresenter {
        return MainPresenter(catsInteractor)
    }
}