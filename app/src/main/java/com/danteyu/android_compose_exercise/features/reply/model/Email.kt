package com.danteyu.android_compose_exercise.features.reply.model

data class Email(
    val id: Int,
    val sender: Account,
    val recipients: List<Account> = emptyList(),
    val subject: String = "",
    val body: String = "",
    var mailbox: MailboxType = MailboxType.Inbox,
    var createdAt: String
)
