package com.danteyu.android_compose_exercise.features.marsphotos

import android.app.Application
import com.danteyu.android_compose_exercise.features.marsphotos.data.AppContainer
import com.danteyu.android_compose_exercise.features.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}