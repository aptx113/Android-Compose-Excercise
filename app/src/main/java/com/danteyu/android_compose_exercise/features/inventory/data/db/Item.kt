package com.danteyu.android_compose_exercise.features.inventory.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danteyu.android_compose_exercise.features.inventory.ui.item.ItemUiState

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
