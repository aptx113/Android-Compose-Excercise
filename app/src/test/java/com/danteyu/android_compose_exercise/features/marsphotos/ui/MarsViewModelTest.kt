package com.danteyu.android_compose_exercise.features.marsphotos.ui

import com.danteyu.android_compose_exercise.features.marsphotos.fake.FakeDataSource
import com.danteyu.android_compose_exercise.features.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.danteyu.android_compose_exercise.features.marsphotos.models.MarsUiState
import com.danteyu.android_compose_exercise.rules.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MarsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(marsPhotosRepository = FakeNetworkMarsPhotosRepository())
        assertEquals(
            MarsUiState.Success(FakeDataSource.photosList),
            marsViewModel.marsUiState
        )
    }
}