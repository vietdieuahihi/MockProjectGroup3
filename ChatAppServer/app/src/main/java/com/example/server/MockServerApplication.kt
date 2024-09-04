package com.example.server

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import com.example.server.data.mockdata.MockData
import com.example.server.data.repository.ChatRepository
import com.example.server.data.repository.ConversationRepository
import com.example.server.data.repository.UserRepository

@HiltAndroidApp
class MockServerApplication : Application() {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var conversationRepository: ConversationRepository

    @Inject
    lateinit var chatRepository: ChatRepository

    override fun onCreate() {
        super.onCreate()

        MockData.insertMockData(userRepository, conversationRepository, chatRepository)
    }
}

