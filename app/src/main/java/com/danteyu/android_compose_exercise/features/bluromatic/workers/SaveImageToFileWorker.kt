package com.danteyu.android_compose_exercise.features.bluromatic.workers

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns.*
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.bluromatic.DELAY_TIME_MILLIS
import com.danteyu.android_compose_exercise.features.bluromatic.KEY_IMAGE_URI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "SaveImageToFileWorker"

class SaveImageToFileWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    private val title = "Blurred Image"
    private val dateFormatter = SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z", Locale.getDefault())

    @RequiresApi(Build.VERSION_CODES.Q)
    override suspend fun doWork(): Result {
        makeStatusNotification(
            applicationContext.resources.getString(R.string.saving_image),
            applicationContext
        )

        return withContext(Dispatchers.IO) {
            delay(DELAY_TIME_MILLIS)

            val resolver = applicationContext.contentResolver
            return@withContext try {
                val resourceUri = inputData.getString(KEY_IMAGE_URI)
                val bitmap =
                    BitmapFactory.decodeStream(resolver.openInputStream(Uri.parse(resourceUri)))
//                val imageUrl = MediaStore.Images.Media.insertImage(
//                    resolver, bitmap, title, dateFormatter.format(
//                        Date()
//                    )
                val imageUrl = saveImage(bitmap, resolver)
                if (!imageUrl.isNullOrEmpty()) {
                    val output = workDataOf(KEY_IMAGE_URI to imageUrl)
                    Result.success(output)
                } else {
                    Log.e(
                        TAG,
                        applicationContext.resources.getString(R.string.writing_to_mediaStore_failed)
                    )
                    Result.failure()
                }
            } catch (exception: Exception) {
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_saving_image),
                    exception
                )
                Result.failure()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun saveImage(bitmap: Bitmap, contentResolver: ContentResolver): String {
        val fileName = "$title+jpg"
        var outputStream: OutputStream? = null
        val imageUri: Uri?
        val contentValues = ContentValues().apply {
            put(DISPLAY_NAME, fileName)
            put(MIME_TYPE, "image/jpg")
            put(RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(IS_PENDING, 1)
        }
        contentResolver.also { resolver ->
            imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            outputStream = imageUri?.let { resolver.openOutputStream(it) }
        }
        outputStream?.use { bitmap.compress(Bitmap.CompressFormat.JPEG, 70, it) }
        contentValues.clear()
        contentValues.put(IS_PENDING, 0)
        imageUri?.let { contentResolver.update(it, contentValues, null, null) }

        return imageUri.toString()
    }
}