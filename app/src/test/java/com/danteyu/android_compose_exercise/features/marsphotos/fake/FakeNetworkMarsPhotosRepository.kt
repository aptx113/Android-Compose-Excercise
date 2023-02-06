package com.danteyu.android_compose_exercise.features.marsphotos.fake

import com.danteyu.android_compose_exercise.features.marsphotos.data.MarsPhotosRepository
import com.danteyu.android_compose_exercise.features.marsphotos.models.MarsPhoto

class FakeNetworkMarsPhotosRepository:MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}