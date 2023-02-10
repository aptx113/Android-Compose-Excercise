@file:OptIn(ExperimentalMaterial3Api::class)

package com.danteyu.android_compose_exercise.features.inventory.ui.item

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.inventory.InventoryTopAppBar
import com.danteyu.android_compose_exercise.features.inventory.ui.InventoryViewModelProvider
import com.danteyu.android_compose_exercise.features.inventory.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object ItemEditDestination : NavigationDestination {
    override val route: String = "item_edit"
    override val titleRes: Int = R.string.edit_item_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemEditViewModel = viewModel(factory = InventoryViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        InventoryTopAppBar(
            title = stringResource(id = ItemEditDestination.titleRes),
            canNavigateBack = true,
            navigateUp = onNavigateUp
        )
    }) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateItem()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemEditRoutePreview() {
    MaterialTheme {
        ItemEditScreen(navigateBack = { }, onNavigateUp = { })
    }
}