package com.danteyu.android_compose_exercise.features.marsphotos.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarsPhoto(
    val id: String,
    @Json(name = "img_src")
    val imgSrc: String
)
