package com.ozman.weatherapptask.data.datasource.repository

import com.ozman.weatherapptask.data.datasource.remote.ApiService
import com.ozman.weatherapptask.data.datasource.remote.response.Forecast
import com.ozman.weatherapptask.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    @Named("api_key") private val apiKey: String,
) : WeatherRepository {

    override suspend fun getForecast(city: String): Flow<List<Forecast>> = flow {
        emit(apiService.getForecast(city = city, apiKey = apiKey).list)
    }.flowOn(Dispatchers.IO)
}
