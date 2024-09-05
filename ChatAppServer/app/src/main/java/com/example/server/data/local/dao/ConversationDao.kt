package com.example.server.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.server.entity.Conversation

@Dao
interface ConversationDao {

    @Insert
    suspend fun insert(conversation: Conversation)

    @Query("SELECT * FROM conversations")
    fun getAllConversations(): LiveData<List<Conversation>>

    @Insert
    suspend fun insertAll(conversations: List<Conversation>)

    @Query("SELECT * FROM conversations")
    suspend fun getRemoteAllConversations(): List<Conversation>

    @Query("SELECT * FROM conversations WHERE conversationId = :conversationId")
    fun getConversationById(conversationId: Int): LiveData<Conversation>

    @Query(
        "UPDATE conversations " +
                "SET lastMessage = :lastMessage, lastMessageId = :lastMessageId, timestamp =:timestamp " +
                "WHERE conversationId = :conversationId"
    )
    suspend fun updateConversation(conversationId: Int, lastMessage: String, lastMessageId: Long, timestamp: String)

    @Query(
        "UPDATE conversations SET timeDeleteSender = :timeDeleteSender, timeDeleteReceiver =:timeDeleteReceiver WHERE conversationId = :conversationId"
    )
    suspend fun updateConversation(conversationId: Int, timeDeleteSender: Long, timeDeleteReceiver: Long)

    @Update
    suspend fun updateConversation(conversation: Conversation)

    @Query("DELETE FROM conversations WHERE conversationId = :conversationId")
    suspend fun deleteConversationById(conversationId: Int)

    @Query("SELECT * FROM conversations WHERE senderId = :userId OR receiverId = :userId ORDER BY timestamp DESC")
    suspend fun getConversationsForUser(userId: Int): List<Conversation>
}