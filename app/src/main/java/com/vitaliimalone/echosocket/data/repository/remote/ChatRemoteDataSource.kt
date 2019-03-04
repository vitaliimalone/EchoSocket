package com.vitaliimalone.echosocket.data.repository.remote

import com.vitaliimalone.echosocket.data.repository.remote.websocket.EchoWebSocketListener
import com.vitaliimalone.echosocket.domain.model.Message
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

@ExperimentalCoroutinesApi
class ChatRemoteDataSource(
    private val okHttpClient: OkHttpClient
) {
    private var ws: WebSocket? = null
    private val listener = EchoWebSocketListener()

    fun connectToChat() {
        val request = Request.Builder().url("wss://echo.websocket.org").build()
        ws = okHttpClient.newWebSocket(request, listener)
    }

    fun sendMessage(message: Message) {
        ws?.send(message.text)
    }

    fun receiveMessage(): ConflatedBroadcastChannel<String>? {
        return listener.receivedMessageChannel
    }
}