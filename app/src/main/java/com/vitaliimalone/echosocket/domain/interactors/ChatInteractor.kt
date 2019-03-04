package com.vitaliimalone.echosocket.domain.interactors

import com.vitaliimalone.echosocket.data.repository.ChatRepository
import com.vitaliimalone.echosocket.domain.model.Message
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

class ChatInteractor(
        private val chatRepository: ChatRepository
) {
    fun connectToChat() {
        chatRepository.connectToChat()
    }

    fun sendMessage(message: Message) {
        chatRepository.sendMessage(message)
    }

    fun receiveMessage(): ConflatedBroadcastChannel<String>? {
        return chatRepository.receiveMessage()
    }
}