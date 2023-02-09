package com.danteyu.android_compose_exercise.features.inventory.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danteyu.android_compose_exercise.features.inventory.data.ItemsRepository
import com.danteyu.android_compose_exercise.features.inventory.data.db.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class InventoryHomeViewModel(itemsRepository: ItemsRepository) : ViewModel() {

    val homeUiState: StateFlow<InventoryHomeUiState> =
        itemsRepository.getAllItemsStream().map { InventoryHomeUiState(it) }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            InventoryHomeUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class InventoryHomeUiState(val itemList: List<Item> = listOf())