package com.example.server.data.repository

import androidx.lifecycle.LiveData
import com.example.server.data.local.dao.ConversationDao
import com.example.server.entity.Conversation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversationRepository @Inject constructor(private val conversationDao: ConversationDao) {

    suspend fun insertConversation(conversation: Conversation): Flow<Unit> = flow {
        emit(conversationDao.insert(conversation))
    }.flowOn(Dispatchers.IO)

    fun getAllConversation(): LiveData<List<Conversation>> {
        return conversationDao.getAllConversations()
    }

    suspend fun updateConversation(
        conversationId: Int,
        lastMessage: String,
        lastMessageId: Long,
        timestamp: String
    ): Flow<Unit> = flow {
        emit(conversationDao.updateConversation(conversationId, lastMessage, lastMessageId, timestamp))
    }.flowOn(Dispatchers.IO)

    suspend fun updateConversation(conversationId: Int, timeDeleteSender: Long, timeDeleteReceiver: Long): Flow<Unit> = flow {
        emit(conversationDao.updateConversation(conversationId, timeDeleteSender, timeDeleteReceiver))
    }.flowOn(Dispatchers.IO)

    suspend fun updateConversation(conversation: Conversation): Flow<Unit> = flow {
        emit(conversationDao.updateConversation(conversation))
    }.flowOn(Dispatchers.IO)

    suspend fun deleteConversationById(conversationId: Int): Flow<Unit> = flow {
        emit(conversationDao.deleteConversationById(conversationId))
    }.flowOn(Dispatchers.IO)

    suspend fun getConversationsForUser(userId: Int): List<Conversation> {
        return conversationDao.getConversationsForUser(userId)
    }
}