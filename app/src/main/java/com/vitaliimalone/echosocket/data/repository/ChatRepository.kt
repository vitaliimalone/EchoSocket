package com.vitaliimalone.echosocket.data.repository

import com.vitaliimalone.echosocket.data.repository.remote.ChatRemoteDataSource
import com.vitaliimalone.echosocket.domain.model.Message
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

@ExperimentalCoroutinesApi
class ChatRepository(
        private val chatRemoteDataSource: ChatRemoteDataSource
) {
    fun connectToChat() {
        chatRemoteDataSource.connectToChat()
    }

    fun sendMessage(message: Message) {
        chatRemoteDataSource.sendMessage(message)
    }

    fun receiveMessage(): ConflatedBroadcastChannel<String>? {
        return chatRemoteDataSource.receiveMessage()
    }
}