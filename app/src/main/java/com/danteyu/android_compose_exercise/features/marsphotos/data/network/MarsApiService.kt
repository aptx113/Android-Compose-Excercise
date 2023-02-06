package com.danteyu.android_compose_exercise.features.marsphotos.data.network

import com.danteyu.android_compose_exercise.features.marsphotos.models.MarsPhoto
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}