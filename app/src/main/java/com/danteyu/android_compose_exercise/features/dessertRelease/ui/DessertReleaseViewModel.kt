package com.danteyu.android_compose_exercise.features.dessertRelease.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.dessertRelease.DessertReleaseApplication
import com.danteyu.android_compose_exercise.features.dessertRelease.data.UserPreferencesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DessertReleaseViewModel(private val userPreferencesRepository: UserPreferencesRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(DessertReleaseUiState())
    val uiState: StateFlow<DessertReleaseUiState> =
        userPreferencesRepository.isLinearLayout.map { isLinearLayout ->
            DessertReleaseUiState(isLinearLayout)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            DessertReleaseUiState()
        )

    fun selectLayout(isLinearLayout: Boolean) {
        viewModelScope.launch { userPreferencesRepository.saveLayoutPreference(isLinearLayout) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = (this[APPLICATION_KEY] as DessertReleaseApplication)
                    DessertReleaseViewModel(application.userPreferencesRepository)
                }
            }
    }
}

data class DessertReleaseUiState(
    val isLinearLayout: Boolean = true,
    val toggleContentDescription: Int = if (isLinearLayout) R.string.grid_layout_toggle else R.string.linear_layout_toggle,
    val toggleIcon: Int = if (isLinearLayout) R.drawable.ic_grid_layout else R.drawable.ic_linear_layout
)