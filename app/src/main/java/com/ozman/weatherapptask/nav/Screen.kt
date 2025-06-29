package com.ozman.newarch.nav

sealed class Screen(val route: String) {
    object Search : Screen("Search")
    object List : Screen("List")
    object Detail : Screen("details/{itemId}") {
        fun createRoute(itemId: Int) = "details/$itemId"
    }
}
