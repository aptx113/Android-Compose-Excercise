package com.danteyu.android_compose_exercise.feature.reply.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.StateRestorationTester
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.annotation.TestCompactWidth
import com.danteyu.android_compose_exercise.annotation.TestExpandedWidth
import com.danteyu.android_compose_exercise.features.reply.data.local.LocalEmailsDataProvider
import com.danteyu.android_compose_exercise.features.reply.ui.ReplyApp
import com.danteyu.android_compose_exercise.onNodeWithContentDescriptionForStringId
import com.danteyu.android_compose_exercise.onNodeWithTagForStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {

    private lateinit var stateRestorationTester: StateRestorationTester

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        stateRestorationTester = StateRestorationTester(composeTestRule)
    }

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }

        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].subject)
            .performClick()

        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.navigation_back)
            .assertExists()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body).assertExists()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.navigation_back)
            .assertExists()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body).assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailEmailRetained_afterConfigChange() {
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Expanded) }

        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].subject).performClick()
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren().assertAny(
            hasAnyDescendant(hasText(LocalEmailsDataProvider.allEmails[2].body))
        )

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren().assertAny(
            hasAnyDescendant(hasText(LocalEmailsDataProvider.allEmails[2].body))
        )
    }
}