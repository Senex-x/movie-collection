package com.senex.moviecollection.presentation.di

import com.senex.moviecollection.data.repository.MovieRepositoryImpl
import com.senex.moviecollection.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bind(repository: MovieRepositoryImpl): MovieRepository
}