package com.ozman.weatherapptask.di

import com.ozman.weatherapptask.BuildConfig
import com.ozman.weatherapptask.data.datasource.remote.ApiService
import com.ozman.weatherapptask.data.datasource.repository.WeatherRepositoryImp
import com.ozman.weatherapptask.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    @Named("api_key")
    fun provideApiKey(): String = BuildConfig.WEATHER_API_KEY

    @Provides
    @Singleton
    fun provideWeatherRepository(
        apiService: ApiService,
        @Named("api_key") apiKey: String,
    ): WeatherRepository {
        return WeatherRepositoryImp(apiService, apiKey)
    }
}