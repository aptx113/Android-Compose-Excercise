package com.danteyu.android_compose_exercise.features.marsphotos.data

import com.danteyu.android_compose_exercise.features.marsphotos.data.network.MarsApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
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