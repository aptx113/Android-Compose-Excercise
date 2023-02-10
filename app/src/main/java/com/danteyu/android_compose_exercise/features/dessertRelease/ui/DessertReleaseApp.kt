package com.danteyu.android_compose_exercise.features.dessertRelease.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.features.dessertRelease.data.LocalDessertReleaseData
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danteyu.android_compose_exercise.R

@Composable
fun DessertReleaseApp(
    modifier: Modifier = Modifier, dessertReleaseViewModel: DessertReleaseViewModel = viewModel(
        factory = DessertReleaseViewModel.Factory
    )
) {
    DessertReleaseApp(
        uiState = dessertReleaseViewModel.uiState.collectAsState().value,
        selectLayout = dessertReleaseViewModel::selectLayout,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DessertReleaseApp(
    uiState: DessertReleaseUiState,
    selectLayout: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val isLinearLayout = uiState.isLinearLayout
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.dessert_release_app_bar)) },
                actions = {
                    IconButton(
                        onClick = { selectLayout(!isLinearLayout) }) {
                        Icon(
                            painter = painterResource(id = uiState.toggleIcon),
                            contentDescription = stringResource(
                                id = uiState.toggleContentDescription
                            ),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )

                    }
                }
            )
        }
    ) { innerPadding ->
        if (isLinearLayout) {
            DessertReleaseLinearLayout(modifier.padding(innerPadding))
        } else {
            DessertReleaseGridLayout(modifier.padding(innerPadding))
        }
    }
}

@Composable
fun DessertReleaseLinearLayout(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = LocalDessertReleaseData.dessertReleases.toList(),
            key = { dessert -> dessert }) { dessert ->
            Card(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = dessert,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DessertReleaseGridLayout(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = LocalDessertReleaseData.dessertReleases,
            key = { dessert -> dessert }) { dessert ->
            Card(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                modifier = Modifier.height(110.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = dessert,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DessertReleaseLinearLayoutPreview() {
    MaterialTheme {
        DessertReleaseLinearLayout()
    }
}

@Preview(showBackground = true)
@Composable
fun DessertReleaseGridLayoutPreview() {
    MaterialTheme {
        DessertReleaseGridLayout()
    }
}

@Preview
@Composable
fun DessertReleaseAppPreview() {
    MaterialTheme {
        DessertReleaseApp(
            uiState = DessertReleaseUiState(),
            selectLayout = {}
        )
    }
}