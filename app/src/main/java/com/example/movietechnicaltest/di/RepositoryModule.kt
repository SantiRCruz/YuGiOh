package com.example.movietechnicaltest.di

import com.example.movietechnicaltest.data.repository.CardsRepoImpl
import com.example.movietechnicaltest.domain.repository.CardsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        moviesRepoImpl: CardsRepoImpl
    ): CardsRepo
}