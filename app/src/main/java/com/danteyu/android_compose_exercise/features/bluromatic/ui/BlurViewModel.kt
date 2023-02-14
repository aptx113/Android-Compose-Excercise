package com.danteyu.android_compose_exercise.features.bluromatic.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.WorkInfo
import com.danteyu.android_compose_exercise.features.bluromatic.BluromaticApplication
import com.danteyu.android_compose_exercise.features.bluromatic.KEY_IMAGE_URI
import com.danteyu.android_compose_exercise.features.bluromatic.data.BlurAmountData
import com.danteyu.android_compose_exercise.features.bluromatic.data.BluromaticRepository
import kotlinx.coroutines.flow.*

class BlurViewModel(private val bluromaticRepository: BluromaticRepository) : ViewModel() {

    internal val blurAmount = BlurAmountData.blurAmount

    val blurUiState: StateFlow<BlurUiState> = bluromaticRepository.outputWorkInfo.map { info ->
        val outputImageUri = info.outputData.getString(KEY_IMAGE_URI)
        when {
            info.state.isFinished && !outputImageUri.isNullOrEmpty() -> {
                BlurUiState.Complete(outputUri = outputImageUri)
            }
            info.state == WorkInfo.State.CANCELLED -> {
                BlurUiState.Default
            }
            else -> BlurUiState.Loading
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), BlurUiState.Default)

    fun applyBlur(blurLevel: Int) {
        bluromaticRepository.applyBlur(blurLevel)
    }

    fun cancelWork() {
        bluromaticRepository.cancelWork()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val bluromaticRepository =
                    (this[APPLICATION_KEY] as BluromaticApplication).container.bluromaticRepository
                BlurViewModel(bluromaticRepository = bluromaticRepository)
            }
        }
    }
}

sealed interface BlurUiState {
    object Default : BlurUiState
    object Loading : BlurUiState
    data class Complete(val outputUri: String) : BlurUiState
}