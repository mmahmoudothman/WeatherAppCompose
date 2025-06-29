package com.ozman.weatherapptask.presentation.search

sealed class SearchEvent {
    data class Search(val cityName: String) : SearchEvent()
    data class MarkAsFirst(val isFirst: Boolean) : SearchEvent()
}
