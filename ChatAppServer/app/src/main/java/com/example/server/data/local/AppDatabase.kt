package com.example.server.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.server.data.local.dao.UserDao
import com.example.server.data.local.dao.ChatDao
import com.example.server.data.local.dao.ConversationDao
import com.example.server.entity.Chat
import com.example.server.entity.Conversation
import com.example.server.entity.User


@Database(
    entities = [User::class, Conversation::class, Chat::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun conversationDao(): ConversationDao
    abstract fun chatDao(): ChatDao

    companion object {
        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext, // Use applicationContext
                AppDatabase::class.java,
                "chat-app.db"
            ).build()
        }
    }
}