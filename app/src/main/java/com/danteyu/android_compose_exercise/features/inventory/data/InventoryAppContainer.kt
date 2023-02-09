package com.danteyu.android_compose_exercise.features.inventory.data

import android.content.Context
import com.danteyu.android_compose_exercise.features.inventory.data.db.InventoryDatabase

interface InventoryAppContainer {
    val itemsRepository: ItemsRepository
}

class InventoryAppDataContainer(private val context: Context) : InventoryAppContainer {
    override val itemsRepository: ItemsRepository
            by lazy { OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao()) }
}