package com.vitaliimalone.echosocket.presentation.chat

import androidx.lifecycle.MutableLiveData
import com.vitaliimalone.echosocket.domain.interactors.ChatInteractor
import com.vitaliimalone.echosocket.domain.model.Message
import com.vitaliimalone.echosocket.presentation.base.BaseViewModel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatInteractor: ChatInteractor
) : BaseViewModel() {
    val messages = MutableLiveData<MutableList<Message>>()
    // in-memory cache
    private val messagesStorage = mutableListOf<Message>()

    init {
        connectToChat()
        subscribeToReceivingMessages()
    }

    private fun connectToChat() {
        chatInteractor.connectToChat()
    }

    fun onSendMessageClick(text: String) {
        val trimmedText = text.trim()
        if (trimmedText.isEmpty()) return
        val message = Message(trimmedText, Message.MessageType.SENT)
        chatInteractor.sendMessage(message)
        messagesStorage.add(message)
        messages.value = messagesStorage
    }

    private fun subscribeToReceivingMessages() {
        uiScope.launch {
            chatInteractor.receiveMessage()?.consumeEach {
                val message = Message(it, Message.MessageType.RECEIVED)
                messagesStorage.add(message)
                messages.value = messagesStorage
            }
        }
    }
}
