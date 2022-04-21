package com.senex.moviecollection.presentation.di

import com.senex.moviecollection.data.api.MovieListApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Singleton
    @Provides
    fun provideMovieListApiService(retrofit: Retrofit): MovieListApiService =
        retrofit.create(MovieListApiService::class.java)
}