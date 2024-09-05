package com.example.client.utils

enum class ConnectionState {
    LOADING,
    CONNECTED,
    DISCONNECTED
}

const val KEY_CONVERSATION = "conversation"
const val KEY_USER = "user"
const val SERVER_PACKAGE_NAME = "com.example.server"
const val SERVER_SERVICE_CLASS_NAME = "com.example.server.service.MessageService"