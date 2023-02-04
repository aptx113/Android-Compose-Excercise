package com.danteyu.android_compose_exercise.features.reply.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danteyu.android_compose_exercise.features.reply.model.Email
import com.danteyu.android_compose_exercise.features.reply.model.MailboxType
import com.danteyu.android_compose_exercise.features.reply.model.ReplyContentType
import com.danteyu.android_compose_exercise.features.reply.model.ReplyNavigationType
import com.danteyu.android_compose_exercise.features.reply.ui.theme.ReplyTheme

@Composable
fun ReplyApp(windowSize: WindowWidthSizeClass, modifier: Modifier = Modifier) {
    ReplyTheme {
        val viewModel: ReplyViewModel = viewModel()
        val replyUiState = viewModel.uiState.collectAsState().value
        val navigationType: ReplyNavigationType
        val contentType: ReplyContentType

        when (windowSize) {
            WindowWidthSizeClass.Compact -> {
                navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
                contentType = ReplyContentType.LIST_ONLY
            }
            WindowWidthSizeClass.Medium -> {
                navigationType = ReplyNavigationType.NAVIGATION_RAIL
                contentType = ReplyContentType.LIST_ONLY
            }
            WindowWidthSizeClass.Expanded -> {
                navigationType = ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
                contentType = ReplyContentType.LIST_AND_DETAIL
            }
            else -> {
                navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
                contentType = ReplyContentType.LIST_ONLY
            }
        }
        ReplyHomeScreen(
            navigationType = navigationType,
            contentType = contentType,
            replyUiState = replyUiState,
            onTabPressed = { mailboxType: MailboxType ->
                viewModel.updateCurrentMailbox(mailboxType = mailboxType)
                viewModel.resetHomeScreenStates()
            },
            onEmailCardPressed = { email: Email -> viewModel.updateDetailsScreenStates(email) },
            onDetailScreenBackPressed = {
                viewModel.resetHomeScreenStates()
            },
            modifier = modifier
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun ReplyAppMediumPreview() {
    ReplyApp(windowSize = WindowWidthSizeClass.Medium)
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ReplyAppExpandedPreview() {
    ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
}