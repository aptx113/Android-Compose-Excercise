package com.danteyu.android_compose_exercise.feature.cupcake.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.feature.cupcake.data.mockFlavors
import com.danteyu.android_compose_exercise.feature.cupcake.data.mockQuantityOptions
import com.danteyu.android_compose_exercise.features.cupcake.model.OrderUiState
import com.danteyu.android_compose_exercise.features.cupcake.ui.OrderSummaryScreen
import com.danteyu.android_compose_exercise.features.cupcake.ui.SelectOptionScreen
import com.danteyu.android_compose_exercise.features.cupcake.ui.StartOrderScreen
import com.danteyu.android_compose_exercise.features.cupcake.ui.theme.CupCakeTheme
import com.danteyu.android_compose_exercise.onNodeWithStringId
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderUiState = OrderUiState(
        quantity = 6,
        flavor = "Vanilla",
        date = "Wed Jul 21",
        price = "$100",
        pickupOptions = listOf()
    )

    @Test
    fun selectOptionScreen_verifyContent() {
        val subTotal = "$100"

        composeTestRule.setContent {
            CupCakeTheme {
                SelectOptionScreen(subtotal = subTotal, options = mockFlavors)
            }
        }
        mockFlavors.forEach { flavour ->
            composeTestRule.onNodeWithText(flavour).assertIsDisplayed()
        }
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subTotal
            )
        ).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            CupCakeTheme {
                StartOrderScreen(quantityOptions = mockQuantityOptions, onNextButtonClicked = {})
            }
        }
        mockQuantityOptions.forEach { option ->
            composeTestRule.onNodeWithStringId(option.first).assertIsDisplayed()
        }
    }

    @Test
    fun selectOptionScreen_optionSelected_NextButtonEnabled() {
        val subTotal = "$100"
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subTotal, options = mockFlavors)
        }
        composeTestRule.onNodeWithText(mockFlavors[0]).performClick()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }

    @Test
    fun summaryScreen_verifyContentDisplay() {
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = fakeOrderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> })
        }

        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                fakeOrderUiState.price
            )
        )
    }
}