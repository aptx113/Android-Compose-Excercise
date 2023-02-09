package com.danteyu.android_compose_exercise.features.inventory.data

import android.content.Context

interface InventoryAppContainer {
    val itemsRepository: ItemsRepository
}

class InventoryAppDataContainer(private val context: Context) : InventoryAppContainer {
    override val itemsRepository: ItemsRepository
            by lazy { OfflineItemsRepository() }
}