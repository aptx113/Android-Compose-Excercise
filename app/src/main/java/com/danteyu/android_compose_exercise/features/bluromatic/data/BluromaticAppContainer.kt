package com.danteyu.android_compose_exercise.features.bluromatic.data

import android.content.Context

interface BluromaticAppContainer {
    val bluromaticRepository: BluromaticRepository
}

class DefaultBluromaticAppContainer(context: Context) : BluromaticAppContainer {
    override val bluromaticRepository: BluromaticRepository =
        WorkManagerBluromaticRepository(context)
}