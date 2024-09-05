package com.example.server.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.server.entity.Chat

@Dao
interface ChatDao {

    @Insert
    suspend fun insert(chat: Chat)

    @Query("SELECT * FROM chats")
    fun getAllChats(): LiveData<List<Chat>>

    @Insert
    suspend fun insertAll(chats: List<Chat>)

    @Query("SELECT * FROM chats")
    suspend fun getRemoteAllChats(): List<Chat>

    @Query("SELECT * FROM chats WHERE chatId = :chatId")
    suspend fun getChatById(chatId: Long): Chat?

    @Query("SELECT * FROM chats WHERE conversationId = :conversationId")
    fun getChatInConversation(conversationId: Int): LiveData<List<Chat>>

    @Query("SELECT * FROM chats WHERE conversationId = :conversationId")
    suspend fun getChatByConversationId(conversationId: Int): List<Chat>

    @Query("UPDATE chats SET flag = 0 WHERE chatId = :chatId")
    suspend fun hideChat(chatId: Long)
}