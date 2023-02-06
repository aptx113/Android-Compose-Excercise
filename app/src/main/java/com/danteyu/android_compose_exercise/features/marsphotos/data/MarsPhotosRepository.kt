package com.danteyu.android_compose_exercise.features.marsphotos.data

import com.danteyu.android_compose_exercise.features.marsphotos.data.network.MarsApiService
import com.danteyu.android_compose_exercise.features.marsphotos.models.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos():List<MarsPhoto>
}

class NetworkMarsPhotosRepository(private val marsApiService: MarsApiService):MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return marsApiService.getPhotos()
    }
}