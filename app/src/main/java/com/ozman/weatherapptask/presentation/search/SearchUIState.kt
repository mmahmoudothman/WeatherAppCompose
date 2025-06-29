package com.ozman.weatherapptask.presentation.search

data class SearchUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    var isFirst: Boolean = false,
    val errorMessage: String? = null
)
