package com.danteyu.android_compose_exercise.features.bluromatic

import android.app.Application
import com.danteyu.android_compose_exercise.features.bluromatic.data.BluromaticAppContainer
import com.danteyu.android_compose_exercise.features.bluromatic.data.DefaultBluromaticAppContainer

class BluromaticApplication : Application() {
    lateinit var container: BluromaticAppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultBluromaticAppContainer(this)
    }
}