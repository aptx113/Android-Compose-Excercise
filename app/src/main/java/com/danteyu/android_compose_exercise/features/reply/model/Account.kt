package com.danteyu.android_compose_exercise.features.reply.model

import androidx.annotation.DrawableRes

data class Account(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    @DrawableRes val avatar: Int
) {
    val fullName: String = "$firstName $lastName"
}
