package com.danteyu.android_compose_exercise.features.marsphotos.data

import com.danteyu.android_compose_exercise.features.marsphotos.data.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

@OptIn(ExperimentalSerializationApi::class)
class DefaultAppContainer : AppContainer {
    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService: MarsApiService by lazy {
        retrofit.create()
    }

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(
            retrofitService
        )
    }
}