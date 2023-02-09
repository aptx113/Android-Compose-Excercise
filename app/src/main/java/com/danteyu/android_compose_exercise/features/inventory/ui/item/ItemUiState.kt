package com.danteyu.android_compose_exercise.features.inventory.ui.item

import com.danteyu.android_compose_exercise.features.inventory.data.db.Item

data class ItemUiState(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
    val actionEnabled: Boolean = false
)

fun ItemUiState.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

fun ItemUiState.isValid(): Boolean =
    name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
