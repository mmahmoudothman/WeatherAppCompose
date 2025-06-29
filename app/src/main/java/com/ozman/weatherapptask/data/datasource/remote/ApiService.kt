package com.ozman.weatherapptask.data.datasource.remote

import com.ozman.weatherapptask.data.datasource.remote.response.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
    ): ForecastResponse
}