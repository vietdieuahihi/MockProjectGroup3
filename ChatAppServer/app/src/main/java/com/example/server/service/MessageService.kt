package com.example.server.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.server.IMessageService
import com.example.server.data.repository.ChatRepository
import com.example.server.data.repository.ConversationRepository
import com.example.server.data.repository.UserRepository
import com.example.server.entity.Chat
import com.example.server.entity.Conversation
import com.example.server.entity.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MessageService : Service() {

    @Inject
    lateinit var chatRepository: ChatRepository

    @Inject
    lateinit var conversationRepository: ConversationRepository

    @Inject
    lateinit var userRepository: UserRepository

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG, "Service bound")
        return mBinder
    }

    private val mBinder = object : IMessageService.Stub() {

        override fun getConversation(): List<Conversation> {
            Log.d(TAG, "getConversation() called")
            return conversationRepository.getAllConversation().value ?: emptyList()
        }

        override fun getUsers(): List<User> {
            return runBlocking(Dispatchers.IO) {
                Log.d(TAG, "getUsers() called")
                userRepository.getRemoteAllUsers()
            }
        }

        override fun getChat(conversationId: Int): List<Chat> {
            return runBlocking(Dispatchers.IO) {

                Log.d(TAG, "getChat() called")
                chatRepository.getChatByConversationId(conversationId)
            }
        }

        override fun getUserById(userId: Int): User? {
            return runBlocking(Dispatchers.IO) {

                Log.d(TAG, "getUserById() called with userId: $userId")
                userRepository.getUserById(userId)
            }
        }

        override fun fetchCurrentUser(): User? {
            return runBlocking(Dispatchers.IO) {

                Log.d(TAG, "getCurrentUser() called")
                userRepository.getCurrentUser()
            }
        }

        override fun switchUser(userId: Int) {
            CoroutineScope(Dispatchers.IO).launch {
                userRepository.switchUser(userId)
            }
        }

        override fun sendMessage(message: Chat?) {
            Log.d(TAG, "sendMessage() called with message: ${message?.message}")
            message?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    chatRepository.insertChat(it).collect {}
                }
            }
        }

        override fun hideChat(chatId: Long) {
            Log.d(TAG, "hideChat() called with chatId: $chatId")
            CoroutineScope(Dispatchers.IO).launch {
                chatRepository.hideChat(chatId).collect {}
            }
        }

        override fun getChatById(chatId: Long): Chat? {
            return runBlocking(Dispatchers.IO) {
                chatRepository.getChatById(chatId)
            }
        }

        override fun removeConversationById(conversationId: Int) {
            CoroutineScope(Dispatchers.IO).launch {
                conversationRepository.deleteConversationById(conversationId).collect {}
            }
        }

        override fun removeUserById(userId: Int) {
            CoroutineScope(Dispatchers.IO).launch {
                userRepository.deleteUserById(userId).collect {}
            }
        }

        override fun updateConversation(
            conversationId: Int,
            lastMessage: String?,
            lastMessageId: Long,
            timestamp: String?
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                conversationRepository.updateConversation(
                    conversationId,
                    lastMessage!!,
                    lastMessageId,
                    timestamp!!
                ).collect {}
            }
        }

        override fun updateConversationV2(
            conversationId: Int,
            timeDeleteSender: Long,
            timeDeleteReceiver: Long
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                conversationRepository.updateConversation(
                    conversationId,
                    timeDeleteSender,
                    timeDeleteReceiver
                ).collect {}
            }
        }

        override fun hideConversation(conversation: Conversation) {
            CoroutineScope(Dispatchers.IO).launch {
                conversationRepository.updateConversation(conversation).collect {}
            }
        }

        override fun getConversationsForUser(userId: Int): MutableList<Conversation> {
            return runBlocking(Dispatchers.IO) {

                Log.d(TAG, "getConversationsForUser() called with userId: $userId")
                val conversations = conversationRepository.getConversationsForUser(userId)
                Log.d(
                    TAG,
                    "Fetched conversations: ${conversations.size} for userId: $userId"
                )
                conversations.toMutableList()
            }
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    companion object {
        private const val TAG = "MessageService"
    }
}