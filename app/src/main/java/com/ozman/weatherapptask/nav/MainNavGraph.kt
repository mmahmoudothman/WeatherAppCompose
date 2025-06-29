package com.ozman.newarch.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ozman.weatherapptask.presentation.details.WeatherDetailScreen
import com.ozman.weatherapptask.presentation.list.ListScreen
import com.ozman.weatherapptask.presentation.search.SearchScreen
import com.ozman.weatherapptask.presentation.search.SearchViewModel

@Composable
fun MainNavGraph(navController: NavHostController = rememberNavController()) {
    val sharedViewModel: SearchViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
            SearchScreen(
                viewModel = sharedViewModel,
                navController = navController
            )
        }
        composable(Screen.List.route) {
            ListScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: return@composable

            val item = sharedViewModel.forecastList.firstOrNull {
                it.temperature.toInt() == itemId
            } ?: return@composable

            WeatherDetailScreen(
                item = item,
                city = sharedViewModel.cityName,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

