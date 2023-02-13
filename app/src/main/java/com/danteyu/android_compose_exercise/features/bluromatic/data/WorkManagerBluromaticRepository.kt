package com.danteyu.android_compose_exercise.features.bluromatic.data

import android.content.Context
import android.net.Uri
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.danteyu.android_compose_exercise.features.bluromatic.KEY_BLUR_LEVEL
import com.danteyu.android_compose_exercise.features.bluromatic.KEY_IMAGE_URI
import com.danteyu.android_compose_exercise.features.bluromatic.workers.BlurWorker
import com.danteyu.android_compose_exercise.getImageUri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class WorkManagerBluromaticRepository(context: Context) : BluromaticRepository {
    override val outputWorkInfo: Flow<WorkInfo?> = MutableStateFlow(null)
    private var imageUri = context.getImageUri()
    private val workManager = WorkManager.getInstance(context)

    override fun applyBlur(blurLevel: Int) {
        val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
        blurBuilder.setInputData(createInputDataForWorkRequest(blurLevel, imageUri))
        workManager.enqueue(blurBuilder.build())
    }

    override fun cancelWork() {
    }

    private fun createInputDataForWorkRequest(blurLevel: Int, imageUri: Uri): Data {
        val builder = Data.Builder()
        builder.putString(KEY_IMAGE_URI, imageUri.toString()).putInt(KEY_BLUR_LEVEL, blurLevel)
        return builder.build()
    }
}