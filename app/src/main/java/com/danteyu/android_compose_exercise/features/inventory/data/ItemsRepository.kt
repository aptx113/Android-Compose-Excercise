package com.danteyu.android_compose_exercise.features.inventory.data

import com.danteyu.android_compose_exercise.features.inventory.data.db.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    fun getAllItemsStream(): Flow<List<Item>>

    fun getItemStream(id: Int): Flow<Item?>

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun updateItem(item: Item)
}