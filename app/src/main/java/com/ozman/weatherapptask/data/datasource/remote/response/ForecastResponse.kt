package com.ozman.weatherapptask.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("list") val list: List<Forecast>,
)

data class Forecast(
    @SerializedName("dt") val timestamp: Long,
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("dt_txt") val dateTimeText: String,
) {
    val temperature: Double get() = main.temp
    val feelsLike: Double get() = main.feelsLike
}

data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
)

data class Weather(
    @SerializedName("main") val condition: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String,
)
