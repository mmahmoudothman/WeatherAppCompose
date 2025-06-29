package com.ozman.weatherapptask.presentation.search

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozman.weatherapptask.data.datasource.remote.response.Forecast
import com.ozman.weatherapptask.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
    private val _forecastList = mutableStateListOf<Forecast>()
    val forecastList: List<Forecast> get() = _forecastList
    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState: StateFlow<SearchUIState> = _uiState.asStateFlow()
    var cityName: String = ""
        private set

    fun onIntent(intent: SearchEvent) {
        when (intent) {
            is SearchEvent.Search -> {
                cityName = intent.cityName
                getForecast(cityName)
            }

            is SearchEvent.MarkAsFirst -> {
                _uiState.value.isFirst = intent.isFirst
            }
        }
    }

    fun getForecast(city: String) {
        viewModelScope.launch {
            _uiState.value = SearchUIState(isLoading = true)
            weatherRepository.getForecast(city).catch {
                _uiState.value = SearchUIState(
                    isLoading = false,
                    isSuccess = false,
                    errorMessage = "Can't Find"
                )
            }
                .collect { response ->
                    setForecasts(response)
                    _uiState.value = SearchUIState(
                        isLoading = false,
                        isSuccess = true,
                        isFirst = true,
                        errorMessage = if (response.isEmpty()) " Empty List" else "Success"
                    )
                }
        }
    }

    fun setForecasts(forecasts: List<Forecast>) {
        _forecastList.clear()
        _forecastList.addAll(forecasts)
    }

    fun resetState() {
        _uiState.value = SearchUIState()
    }
}
