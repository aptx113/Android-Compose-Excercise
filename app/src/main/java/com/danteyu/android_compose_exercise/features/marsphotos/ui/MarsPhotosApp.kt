package com.danteyu.android_compose_exercise.features.marsphotos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.marsphotos.models.MarsUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsPhotosApp(modifier: Modifier = Modifier) {
    MaterialTheme {
        Scaffold(modifier = modifier.fillMaxSize(), topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.mars_photos)) })
        }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.background
            ) {
                val marsViewModel: MarsViewModel = viewModel()
                MarsPhotosScreen(marsUiState = marsViewModel.marsUiState)
            }
        }
    }
}

@Composable
private fun MarsPhotosScreen(marsUiState: MarsUiState, modifier: Modifier = Modifier) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier)
        is MarsUiState.Success -> ResultScreen(marsUiState = marsUiState.photos, modifier)
        is MarsUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
private fun ResultScreen(marsUiState: String, modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Text(text = marsUiState)
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(
                id = R.string.loading
            ),
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.loading_failed))
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MaterialTheme {
        ResultScreen(marsUiState = stringResource(id = R.string.placeholder_result))
    }
}