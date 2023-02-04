package com.danteyu.android_compose_exercise.features.marsphotos.models

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}