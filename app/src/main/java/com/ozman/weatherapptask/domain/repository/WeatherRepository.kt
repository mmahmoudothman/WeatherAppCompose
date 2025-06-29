package com.ozman.weatherapptask.domain.repository

import com.ozman.weatherapptask.data.datasource.remote.response.Forecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getForecast(city: String): Flow<List<Forecast>>
}