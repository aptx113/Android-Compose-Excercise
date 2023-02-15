package com.danteyu.android_compose_exercise.features.bluromatic.workers

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.workDataOf
import com.danteyu.android_compose_exercise.features.bluromatic.KEY_IMAGE_URI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WorkerInstrumentationTest {
    private lateinit var context: Context
    private val mockUriInput =
        KEY_IMAGE_URI to "android.resource://com.danteyu.android_compose_exercise/drawable/android_cupcake"

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun cleanupWorker_doWork_resultSuccess() {
        val worker = TestListenableWorkerBuilder<CleanupWorker>(context).build()
        runTest {
            val result = worker.doWork()
            assertThat(result, `is`(ListenableWorker.Result.success()))
        }
    }

    @Test
    fun blurWorker_doWork_resultSuccessReturnsUri() {
        val worker =
            TestListenableWorkerBuilder<BlurWorker>(context).setInputData(workDataOf(mockUriInput))
                .build()
        runTest {
            val result = worker.doWork()
            val resultUri = result.outputData.getString(KEY_IMAGE_URI)
            Log.i("worker", "$result")
            assertTrue(result is ListenableWorker.Result.Success)
            assertTrue(result.outputData.keyValueMap.containsKey(KEY_IMAGE_URI))
            assertTrue(
                resultUri?.startsWith("file:///data/user/0/com.danteyu.android_compose_exercise/files/blur_filter_outputs/blur-filter-output-")
                    ?: false
            )
        }
    }

    @Test
    fun saveImageToFileWorker_doWork_resultSuccessReturnsUrl() {
        val worker = TestListenableWorkerBuilder<SaveImageToFileWorker>(context).setInputData(
            workDataOf(mockUriInput)
        ).build()
        runTest {
            val result = worker.doWork()
            val resultUri = result.outputData.getString(KEY_IMAGE_URI)
            assertTrue(result is ListenableWorker.Result.Success)
            assertTrue(result.outputData.keyValueMap.containsKey(KEY_IMAGE_URI))
            assertTrue(resultUri?.startsWith("content://media/external/images/media/") ?: false)
        }
    }
}