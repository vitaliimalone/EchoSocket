package com.vitaliimalone.echosocket.data.repository.remote.websocket

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class EchoWebSocketListener : WebSocketListener() {
    var receivedMessageChannel: ConflatedBroadcastChannel<String>? = null

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        receivedMessageChannel = ConflatedBroadcastChannel()
        webSocket.send("Hello world!")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        receivedMessageChannel?.offer(text)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        receivedMessageChannel?.close()
    }

}