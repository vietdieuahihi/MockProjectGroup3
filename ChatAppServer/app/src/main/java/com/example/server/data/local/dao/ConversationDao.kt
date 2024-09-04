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
    fun insert(conversation: Conversation)

    @Query("SELECT * FROM conversations")
    fun getAllConversations(): LiveData<List<Conversation>>

    @Insert
    fun insertAll(conversations: List<Conversation>)

    @Query("SELECT * FROM conversations")
    fun getRemoteAllConversations(): List<Conversation>

    @Query("SELECT * FROM conversations WHERE conversationId = :conversationId")
    fun getConversationById(conversationId: Int): LiveData<Conversation>

    @Query(
        "UPDATE conversations " +
                "SET lastMessage = :lastMessage, timestamp =:timestamp " +
                "WHERE conversationId = :conversationId"
    )
    fun updateConversation(conversationId: Int, lastMessage: String, timestamp: String)

    @Query(
        "UPDATE conversations " + "SET timeDeleteSender = :timeDeleteSender, timeDeleteReceiver =:timeDeleteReceiver " + "WHERE conversationId = :conversationId"
    )
    fun updateConversation(conversationId: Int, timeDeleteSender: Long, timeDeleteReceiver: Long)

    @Update
    fun updateConversation(conversation: Conversation)

    @Query("DELETE FROM conversations WHERE conversationId = :conversationId")
    fun deleteConversationById(conversationId: Int)

    @Query("SELECT * FROM conversations WHERE senderId = :userId OR receiverId = :userId ORDER BY timestamp DESC")
    fun getConversationsForUser(userId: Int): List<Conversation>
}
