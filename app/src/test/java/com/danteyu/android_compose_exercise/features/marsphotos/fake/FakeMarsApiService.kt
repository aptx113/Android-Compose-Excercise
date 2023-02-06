package com.danteyu.android_compose_exercise.features.marsphotos.fake

import com.danteyu.android_compose_exercise.features.marsphotos.data.network.MarsApiService
import com.danteyu.android_compose_exercise.features.marsphotos.models.MarsPhoto

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}