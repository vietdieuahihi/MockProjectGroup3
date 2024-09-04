package com.example.server.di

import android.content.Context
import androidx.room.Room
import com.example.server.data.local.AppDatabase
import com.example.server.data.local.dao.UserDao
import com.example.server.data.local.dao.ChatDao
import com.example.server.data.local.dao.ConversationDao
import com.example.server.data.repository.ChatRepository
import com.example.server.data.repository.ConversationRepository
import com.example.server.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        val database = AppDatabase.getDatabase(context)
        return database
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    fun provideChatDao(database: AppDatabase): ChatDao = database.chatDao()

    @Provides
    fun provideConversationDao(database: AppDatabase): ConversationDao = database.conversationDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepository(userDao)

    @Provides
    @Singleton
    fun provideConversationRepository(conversationDao: ConversationDao): ConversationRepository =
        ConversationRepository(conversationDao)

    @Provides
    @Singleton
    fun provideChatRepository(chatDao: ChatDao): ChatRepository = ChatRepository(chatDao)
}