package com.danteyu.android_compose_exercise.features.dessertRelease.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.danteyu.android_compose_exercise.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertReleaseViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(DessertReleaseUiState())
    val uiState = _uiState.asStateFlow()

    fun selectLayout(isLinearLayout: Boolean) {
        _uiState.update { DessertReleaseUiState(isLinearLayout) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory { initializer { DessertReleaseViewModel() } }
    }
}

data class DessertReleaseUiState(
    val isLinearLayout: Boolean = true,
    val toggleContentDescription: Int = if (isLinearLayout) R.string.grid_layout_toggle else R.string.linear_layout_toggle,
    val toggleIcon: Int = if (isLinearLayout) R.drawable.ic_grid_layout else R.drawable.ic_linear_layout
)