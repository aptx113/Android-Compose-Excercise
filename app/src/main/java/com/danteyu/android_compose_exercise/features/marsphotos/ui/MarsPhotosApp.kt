package com.danteyu.android_compose_exercise.features.marsphotos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.marsphotos.models.MarsPhoto
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
                val marsViewModel: MarsViewModel = viewModel(factory = MarsViewModel.Factory)
                MarsPhotosScreen(
                    marsUiState = marsViewModel.marsUiState,
                    retryAction = marsViewModel::getMarsPhotos
                )
            }
        }
    }
}

@Composable
private fun MarsPhotosScreen(
    marsUiState: MarsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier)
        is MarsUiState.Success -> PhotosGridScreen(photos = marsUiState.photos, modifier)
        is MarsUiState.Error -> ErrorScreen(retryAction, modifier)
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
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun PhotosGridScreen(photos: List<MarsPhoto>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = photos, key = { photo -> photo.id }) { photo ->
            MarsPhotoCard(photo = photo)
        }
    }
}

@Composable
private fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(id = R.string.mars_photo),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MaterialTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MaterialTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MaterialTheme {
        val mockData = List(10) { MarsPhoto("$it", "") }
        PhotosGridScreen(mockData)
    }
}