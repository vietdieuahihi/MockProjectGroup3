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
import com.example.server.entity.User
import com.example.server.entity.Conversation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MessageService : Service() {

    @Inject
    lateinit var chatRepository: ChatRepository

    @Inject
    lateinit var conversationRepository: ConversationRepository

    @Inject
    lateinit var userRepository: UserRepository

    override fun onBind(intent: Intent?): IBinder {
        Log.d("MockServer", "Service bound")
        return mBinder
    }

    private val mBinder = object : IMessageService.Stub() {

        override fun getConversation(): List<Conversation> {
            Log.d("MockServer", "getConversation() called")
            return conversationRepository.getAllConversation().value ?: emptyList()
        }

        override fun getUsers(): List<User> {
            Log.d("MockServer", "getUsers() called")
            return userRepository.getRemoteAllUsers()
        }

        override fun getChat(conversationId: Int): List<Chat> {
            Log.d("MockServer", "getChat() called")
            return chatRepository.getChatByConversationId(conversationId)
        }

        override fun getUserById(userId: Int): User? {
            Log.d("MockServer", "getUserById() called with userId: $userId")
            return userRepository.getUserById(userId)
        }

        override fun fetchCurrentUser(): User? {
            Log.d("MockServer", "getCurrentUser() called")
            return userRepository.getCurrentUser()
        }

        override fun switchUser(userId: Int) {
            CoroutineScope(Dispatchers.IO).launch {
                userRepository.switchUser(userId)
            }
        }

        override fun sendMessage(message: Chat?) {
            Log.d("MockServer", "sendMessage() called with message: ${message?.message}")
            message?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    chatRepository.insertChat(it).collect {}
                }
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
            timestamp: String?
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                conversationRepository.updateConversation(
                    conversationId,
                    lastMessage!!,
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
            Log.d("MockServer", "getConversationsForUser() called with userId: $userId")
            val conversations = conversationRepository.getConversationsForUser(userId)
            Log.d("MockServer", "Fetched conversations: ${conversations.size} for userId: $userId")
            return conversations.toMutableList()
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
}