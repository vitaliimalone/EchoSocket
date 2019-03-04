package com.vitaliimalone.echosocket.domain.model

data class Message(
    val text: String,
    val type: MessageType,
    val time: Long = System.currentTimeMillis()
) {
    enum class MessageType { SENT, RECEIVED }
}