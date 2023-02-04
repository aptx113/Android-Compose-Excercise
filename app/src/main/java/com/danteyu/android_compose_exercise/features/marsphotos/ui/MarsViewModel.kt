package com.danteyu.android_compose_exercise.features.marsphotos.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danteyu.android_compose_exercise.features.marsphotos.data.network.MarsApi
import com.danteyu.android_compose_exercise.features.marsphotos.models.MarsUiState
import kotlinx.coroutines.launch
import java.io.IOException

class MarsViewModel : ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                marsUiState =
                    MarsUiState.Success("Success: ${listResult.size} Mars photos retrieved")
            } catch (e: IOException) {
                marsUiState = MarsUiState.Error
            }
        }
    }
}