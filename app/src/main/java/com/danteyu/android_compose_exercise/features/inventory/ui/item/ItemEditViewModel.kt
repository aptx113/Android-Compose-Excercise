package com.danteyu.android_compose_exercise.features.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danteyu.android_compose_exercise.features.inventory.data.ItemsRepository
import com.danteyu.android_compose_exercise.features.inventory.data.db.toItemUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        getItemById()
    }

    private fun getItemById() = viewModelScope.launch {
        itemUiState = itemsRepository.getItemStream(itemId)
            .filterNotNull()
            .first()
            .toItemUiState(actionEnabled = true)
    }

    fun updateUiState(newItemUiState: ItemUiState) {
        itemUiState = newItemUiState.copy(actionEnabled = newItemUiState.isValid())
    }

    suspend fun updateItem() {
        if (itemUiState.isValid()) {
            itemsRepository.updateItem(itemUiState.toItem())
        }
    }
}