package com.danteyu.android_compose_exercise.features.inventory.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.danteyu.android_compose_exercise.features.inventory.InventoryApplication
import com.danteyu.android_compose_exercise.features.inventory.ui.home.InventoryHomeViewModel
import com.danteyu.android_compose_exercise.features.inventory.ui.item.ItemDetailsViewModel
import com.danteyu.android_compose_exercise.features.inventory.ui.item.ItemEditViewModel
import com.danteyu.android_compose_exercise.features.inventory.ui.item.ItemEntryViewModel

object InventoryViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ItemDetailsViewModel(this.createSavedStateHandle())
        }
        initializer {
            ItemEditViewModel(this.createSavedStateHandle())
        }
        initializer {
            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
        }

        initializer { InventoryHomeViewModel() }
    }
}


fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)