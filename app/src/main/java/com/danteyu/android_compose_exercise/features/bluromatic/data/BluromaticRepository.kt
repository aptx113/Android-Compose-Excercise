package com.danteyu.android_compose_exercise.features.bluromatic.data

import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

interface BluromaticRepository {
    val outputWorkInfo:Flow<WorkInfo>
    fun applyBlur(blurLevel:Int)
    fun cancelWork()
}