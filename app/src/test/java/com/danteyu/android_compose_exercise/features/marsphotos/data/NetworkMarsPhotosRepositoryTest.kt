package com.danteyu.android_compose_exercise.features.marsphotos.data

import com.danteyu.android_compose_exercise.features.marsphotos.fake.FakeMarsApiService
import com.danteyu.android_compose_exercise.features.marsphotos.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMarsPhotosRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = NetworkMarsPhotosRepository(marsApiService = FakeMarsApiService())
        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }
}