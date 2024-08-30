package com.example.server.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.server.entity.Conversation


@Dao
interface ConversationDao {
    @Insert
    fun insert(conversation: Conversation)

    @Query("SELECT * FROM conversations")
    fun getAllConversations(): LiveData<List<Conversation>>

    @Query("SELECT * FROM conversations")
    fun getRemoteAllConversations(): List<Conversation>

    @Query("SELECT * FROM conversations WHERE conversationId = :conversationId")
    fun getConversationById(conversationId: Long): LiveData<Conversation>

    @Query("UPDATE conversations " +
            "SET lastMessage = :lastMessage, timestamp =:timestamp " +
            "WHERE conversationId = :conversationId")
    fun updateConversation(conversationId: Long, lastMessage: String, timestamp: String)

    @Query("DELETE FROM conversations WHERE conversationId = :conversationId")
    fun deleteConversationById(conversationId: Long)


}
