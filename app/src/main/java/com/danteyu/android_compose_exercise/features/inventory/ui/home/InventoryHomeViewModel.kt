package com.danteyu.android_compose_exercise.features.inventory.ui.home

import androidx.lifecycle.ViewModel
import com.danteyu.android_compose_exercise.features.inventory.data.ItemsRepository
import com.danteyu.android_compose_exercise.features.inventory.data.db.Item

class InventoryHomeViewModel() : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class InventoryHomeUiState(val itemList: List<Item> = listOf())