package com.danteyu.android_compose_exercise.features.topic.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResource: Int,
    val availableCourses: Int,
    @DrawableRes val drawableResource: Int
)
