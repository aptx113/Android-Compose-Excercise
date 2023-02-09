package com.danteyu.android_compose_exercise.features.inventory.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.inventory.InventoryTopAppBar
import com.danteyu.android_compose_exercise.features.inventory.data.db.Item
import com.danteyu.android_compose_exercise.features.inventory.ui.navigation.NavigationDestination
import java.text.NumberFormat

object InventoryHomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.inventory
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryHomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(id = InventoryHomeDestination.titleRes),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.item_entry_title),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
        InventoryHomeBody(
            itemList = listOf(),
            onItemClick = navigateToItemUpdate,
            modifier = modifier.padding(innerPadding)
        )

    }
}

@Composable
private fun InventoryHomeBody(
    itemList: List<Item>,
    onItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InventoryListHeader()
        Divider()
        if (itemList.isEmpty()) {
            Text(
                text = stringResource(id = R.string.no_item_description),
                style = MaterialTheme.typography.titleSmall
            )
        } else {
            InventoryList(itemList = itemList, onItemClick = { onItemClick(it) })
        }
    }
}

@Composable
private fun InventoryList(
    itemList: List<Item>,
    onItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = itemList, key = { it.id }) { item ->
            InventoryItem(item = item, onItemClick = onItemClick)
            Divider()
        }
    }
}

@Composable
private fun InventoryListHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        headerList.forEach {
            Text(
                text = stringResource(id = it.headerStringId),
                modifier = Modifier.weight(it.weight),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
private fun InventoryItem(item: Item, onItemClick: (Item) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onItemClick(item) }
        .padding(vertical = 16.dp))
    {
        Text(text = item.name, modifier = Modifier.weight(1.5f), fontWeight = FontWeight.Bold)
        Text(
            text = NumberFormat.getCurrencyInstance().format(item.price),
            modifier = Modifier.weight(1.0f)
        )
        Text(text = item.quantity.toString(), modifier = Modifier.weight(1.0f))
    }
}

private data class InventoryHeader(@StringRes val headerStringId: Int, val weight: Float)

private val headerList = listOf(
    InventoryHeader(headerStringId = R.string.item, weight = 1.5f),
    InventoryHeader(headerStringId = R.string.price, weight = 1.0f),
    InventoryHeader(headerStringId = R.string.quantity_in_stock, weight = 1.0f)
)

@Preview(showBackground = true)
@Composable
private fun PreviewInventoryHomeScreen() {
    MaterialTheme {
        InventoryHomeBody(
            itemList = listOf(
                Item(1, "Game", 100.0, 20),
                Item(2, "Pen", 200.0, 30), Item(3, "TV", 300.0, 50)
            ),
            onItemClick = {}
        )
    }
}