package com.example.server.data.repository

import androidx.lifecycle.LiveData
import com.example.server.data.local.dao.ChatDao
import com.example.server.entity.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(private val chatDao: ChatDao) {

    suspend fun insertChat(chat: Chat): Flow<Unit> = flow {
        emit(chatDao.insert(chat))
    }.flowOn(Dispatchers.IO)

    fun getChatInConversation(conversationId: Int): LiveData<List<Chat>> {
        return chatDao.getChatInConversation(conversationId)
    }

    suspend fun getChatByConversationId(conversationId: Int): List<Chat> {
        return chatDao.getChatByConversationId(conversationId)
    }

    suspend fun getChatById(chatId: Long): Chat? {
        return chatDao.getChatById(chatId)
    }

    suspend fun hideChat(chatId: Long): Flow<Unit> = flow {
        emit(chatDao.hideChat(chatId))
    }.flowOn(Dispatchers.IO)
}