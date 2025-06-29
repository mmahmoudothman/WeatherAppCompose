package com.ozman.weatherapptask.presentation.search
import com.ozman.weatherapptask.data.datasource.remote.response.Forecast
import com.ozman.weatherapptask.data.datasource.remote.response.Main
import com.ozman.weatherapptask.data.datasource.remote.response.Weather
import com.ozman.weatherapptask.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: SearchViewModel
    private val weatherRepository: WeatherRepository = mockk()

    private val forecastDummy = listOf(
        Forecast(
            timestamp = 1234567890,
            main = Main(temp = 25.0, feelsLike = 27.0, tempMin = 20.0, tempMax = 28.0, pressure = 1012, humidity = 70),
            weather = listOf(Weather("Clear", "clear sky", "01d")),
            dateTimeText = "2025-06-29 12:00:00"
        ),
        Forecast(
            timestamp = 1234567891,
            main = Main(temp = 22.0, feelsLike = 23.5, tempMin = 21.0, tempMax = 24.0, pressure = 1010, humidity = 65),
            weather = listOf(Weather("Clouds", "few clouds", "02d")),
            dateTimeText = "2025-06-29 15:00:00"
        )
    )


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel(weatherRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `search success should update forecastList and UI state`() = runTest {
        coEvery { weatherRepository.getForecast("Dallas") } returns flow {
            emit(forecastDummy)
        }

        viewModel.onIntent(SearchEvent.Search("Dallas"))

        advanceUntilIdle()

        assertThat(viewModel.forecastList).isEqualTo(forecastDummy)
        assertThat(viewModel.uiState.value.isSuccess).isTrue()
        assertThat(viewModel.uiState.value.isLoading).isFalse()
    }

    @Test
    fun `search failure should update UI state with error`() = runTest {
        coEvery { weatherRepository.getForecast("Invalid") } returns flow {
            throw Exception("City not found")
        }

        viewModel.onIntent(SearchEvent.Search("Invalid"))

        advanceUntilIdle()

        assertThat(viewModel.uiState.value.isSuccess).isFalse()
        assertThat(viewModel.uiState.value.isLoading).isFalse()
        assertThat(viewModel.uiState.value.errorMessage).isEqualTo("Can't Find")
    }

    @Test
    fun `MarkAsFirst event should update isFirst`() = runTest {
        viewModel.onIntent(SearchEvent.MarkAsFirst(true))
        assertThat(viewModel.uiState.value.isFirst).isTrue()
    }

    @Test
    fun `resetState should return UI state to default`() = runTest {
        viewModel.resetState()
        assertThat(viewModel.uiState.value).isEqualTo(SearchUIState())
    }
}

