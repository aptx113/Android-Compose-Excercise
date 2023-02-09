package com.danteyu.android_compose_exercise.features.inventory.data.db

import com.danteyu.android_compose_exercise.features.inventory.ui.item.ItemUiState

data class Item(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int
)

fun Item.toItemUiState(actionEnabled: Boolean = false): ItemUiState = ItemUiState(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString(),
    actionEnabled = actionEnabled
)
