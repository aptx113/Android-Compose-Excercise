package com.danteyu.android_compose_exercise.features.inventory.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danteyu.android_compose_exercise.features.inventory.data.ItemsRepository
import com.danteyu.android_compose_exercise.features.inventory.data.db.toItemUiState
import kotlinx.coroutines.flow.*

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    val uiState: StateFlow<ItemUiState> =
        itemsRepository.getItemStream(itemId)
            .filterNotNull()
            .map { it.toItemUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                ItemUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}