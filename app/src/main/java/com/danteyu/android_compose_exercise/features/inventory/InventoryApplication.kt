package com.danteyu.android_compose_exercise.features.inventory

import android.app.Application
import com.danteyu.android_compose_exercise.features.inventory.data.InventoryAppContainer
import com.danteyu.android_compose_exercise.features.inventory.data.InventoryAppDataContainer

class InventoryApplication : Application() {
    lateinit var container: InventoryAppContainer
    override fun onCreate() {
        super.onCreate()
        container = InventoryAppDataContainer(this)
    }
}