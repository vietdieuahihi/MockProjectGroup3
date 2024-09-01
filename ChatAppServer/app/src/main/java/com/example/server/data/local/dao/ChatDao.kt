package com.example.server.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.server.entity.Chat

@Dao
interface ChatDao {
    @Insert
    fun insert(chat: Chat)

    @Query("SELECT * FROM chats")
    fun getAllChats(): LiveData<List<Chat>>

    @Query("SELECT * FROM chats")
    fun getRemoteAllChats(): List<Chat>

    @Query("SELECT * FROM chats WHERE conversationId = :conversationId")
    fun getChatInConversation(conversationId: Long): LiveData<List<Chat>>
}
