package com.danteyu.android_compose_exercise.features.reply.ui

import androidx.lifecycle.ViewModel
import com.danteyu.android_compose_exercise.features.reply.data.local.LocalEmailsDataProvider
import com.danteyu.android_compose_exercise.features.reply.model.Email
import com.danteyu.android_compose_exercise.features.reply.model.MailboxType
import com.danteyu.android_compose_exercise.features.reply.model.ReplyUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReplyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ReplyUiState())
    val uiState = _uiState.asStateFlow()

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        val mailboxes = LocalEmailsDataProvider.allEmails.groupBy { it.mailbox }
        _uiState.value = ReplyUiState(
            mailboxes = mailboxes,
            currentSelectedEmail = mailboxes[MailboxType.Inbox]?.get(0)
                ?: LocalEmailsDataProvider.defaultEmail
        )
    }

    fun updateDetailsScreenStates(email: Email) {
        _uiState.update { it.copy(currentSelectedEmail = email, isShowingHomepage = false) }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedEmail = it.mailboxes[it.currentMailbox]?.get(0)
                    ?: LocalEmailsDataProvider.defaultEmail, isShowingHomepage = true
            )
        }
    }

    fun updateCurrentMailbox(mailboxType: MailboxType) {
        _uiState.update { it.copy(currentMailbox = mailboxType) }
    }
}