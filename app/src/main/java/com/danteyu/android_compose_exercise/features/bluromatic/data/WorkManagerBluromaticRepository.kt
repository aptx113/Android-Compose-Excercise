package com.danteyu.android_compose_exercise.features.bluromatic.data

import android.content.Context
import android.net.Uri
import androidx.work.*
import com.danteyu.android_compose_exercise.features.bluromatic.IMAGE_MANIPULATION_WORK_NAME
import com.danteyu.android_compose_exercise.features.bluromatic.KEY_BLUR_LEVEL
import com.danteyu.android_compose_exercise.features.bluromatic.KEY_IMAGE_URI
import com.danteyu.android_compose_exercise.features.bluromatic.TAG_OUTPUT
import com.danteyu.android_compose_exercise.features.bluromatic.workers.BlurWorker
import com.danteyu.android_compose_exercise.features.bluromatic.workers.CleanupWorker
import com.danteyu.android_compose_exercise.features.bluromatic.workers.SaveImageToFileWorker
import com.danteyu.android_compose_exercise.getImageUri
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.mapNotNull

class WorkManagerBluromaticRepository(context: Context) : BluromaticRepository {
    private var imageUri = context.getImageUri()
    private val workManager = WorkManager.getInstance(context)

    override val outputWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData(TAG_OUTPUT).asFlow()
            .mapNotNull { if (it.isNotEmpty()) it.first() else null }

    override fun applyBlur(blurLevel: Int) {
        var continuation = workManager.beginUniqueWork(
            IMAGE_MANIPULATION_WORK_NAME, ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.Companion.from(CleanupWorker::class.java)
        )
        val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
        blurBuilder.setInputData(createInputDataForWorkRequest(blurLevel, imageUri))
        continuation = continuation.then(blurBuilder.build())
        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>().addTag(TAG_OUTPUT).build()
        continuation = continuation.then(save)
        continuation.enqueue()
    }

    override fun cancelWork() {
    }

    private fun createInputDataForWorkRequest(blurLevel: Int, imageUri: Uri): Data {
        val builder = Data.Builder()
        builder.putString(KEY_IMAGE_URI, imageUri.toString()).putInt(KEY_BLUR_LEVEL, blurLevel)
        return builder.build()
    }
}