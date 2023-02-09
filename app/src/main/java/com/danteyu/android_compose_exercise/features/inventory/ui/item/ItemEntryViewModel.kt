package com.danteyu.android_compose_exercise.features.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.danteyu.android_compose_exercise.features.inventory.data.ItemsRepository

class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    fun updateUiState(newItemUiState: ItemUiState) {
        itemUiState = newItemUiState.copy(actionEnabled = newItemUiState.isValid())
    }

    suspend fun saveItem() {
        if (itemUiState.isValid()) {
            itemsRepository.insertItem(itemUiState.toItem())
        }
    }
}