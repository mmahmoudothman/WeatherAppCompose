package com.ozman.weatherapptask.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ozman.newarch.nav.Screen

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavController,
) {
    DisposableEffect(Unit) {
        viewModel.resetState()
        onDispose { }
    }

    var cityName by remember { mutableStateOf("") }
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = cityName,
            onValueChange = { cityName = it },
            label = { Text("City Name") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (cityName.isNotBlank()) {
                    viewModel.onIntent(SearchEvent.Search(cityName.trim()))
                }
            },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            enabled = !state.isLoading
        ) {
            Text(if (state.isLoading) "Searching..." else "Look up")
        }

        state.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it)
        }

        if (state.isSuccess) {
            LaunchedEffect(Unit) {
                if (state.isFirst) {
                    viewModel.onIntent(SearchEvent.MarkAsFirst(false))
                    navController.navigate(Screen.List.route)
                }
            }
        }
    }
}