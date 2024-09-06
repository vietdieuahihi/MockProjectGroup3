package com.example.client.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.server.IMessageService
import com.example.server.entity.Chat
import com.example.server.entity.Conversation
import com.example.server.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val context: Context) {

    private var messageService: IMessageService? = null
    private val _users = MutableLiveData<List<User>>()
    private val _currentUser = MutableLiveData<User>()
    private val _userById = MutableLiveData<User>()
    private val _conversations = MutableLiveData<List<Conversation>>()

    val users: LiveData<List<User>> get() = _users
    val currentUser: LiveData<User> get() = _currentUser
    val userById: LiveData<User> get() = _userById
    val conversations: LiveData<List<Conversation>> get() = _conversations

    fun initService(messageService: IMessageService) {
        if (this.messageService == null) {
            this.messageService = messageService
        }
    }

    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userList = messageService?.getUsers() ?: emptyList()
                Log.d(TAG, "Fetched users: ${userList.size} users received.")
                _users.postValue(userList.sortedBy { it.userId })
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching users: ${e.message}", e)
            }
        }
    }

    fun fetchConversationsForUser(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val conversationList = messageService?.getConversationsForUser(userId) ?: emptyList()
                Log.d(TAG, "Fetched conversations for user $userId: ${conversationList.size} conversations received.")
                _conversations.postValue(conversationList)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching conversations for user $userId: ${e.message}", e)
            }
        }
    }

    fun hideConversation(conversation: Conversation) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                messageService?.hideConversation(conversation)
            } catch (e: Exception) {
                Log.e(TAG, "Error hide conversations: ${e.message}", e)
            }
        }
    }

    fun updateConversation(conversationId: Int, lastMessage: String, lastMessageId: Long, timestamp: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                messageService?.updateConversation(conversationId, lastMessage, lastMessageId, timestamp)
            } catch (e: Exception) {
                Log.e(TAG, "Error hide conversations: ${e.message}", e)
            }
        }
    }

    fun updateConversationV2(conversationId: Int, timeDeleteSender: Long, timeDeleteReceiver: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                messageService?.updateConversationV2(conversationId, timeDeleteSender, timeDeleteReceiver)
            } catch (e: Exception) {
                Log.e(TAG, "Error hide conversations: ${e.message}", e)
            }
        }
    }

    fun getUserById(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = messageService?.getUserById(userId)
                user?.let {
                    _userById.postValue(it)
                    Log.d(TAG, "Fetched user with ID $userId: ${it.username}")
                } ?: Log.e(TAG, "User with ID $userId not found.")
            } catch (e: Exception) {
                Log.d(TAG, "Error")
                Log.e(TAG, "Error fetching user by ID: ${e.message}", e)
            }
        }
    }

    fun fetchUserById(userId: Int): User? = messageService?.getUserById(userId)

    fun fetchCurrentUser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = messageService?.fetchCurrentUser()
                user?.let {
                    _currentUser.postValue(it)
                    Log.d(TAG, "Fetched user with ID ${it.userId}: ${it.username}")
                } ?: Log.e(TAG, "Current user not found.")
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching current user: ${e.message}", e)
            }
        }
    }

    fun switchUser(userId: Int) {
        messageService?.switchUser(userId)
    }

    fun getAllChatsByConversationId(conversationId: Int): MutableList<Chat>? = messageService?.getChat(conversationId)

    fun sendMessage(chat: Chat) {
        messageService?.sendMessage(chat)
    }

    fun hideChat(chatId: Long) {
        messageService?.hideChat(chatId)
    }

    fun getChatById(chatId: Long) = messageService?.getChatById(chatId)

    companion object {
        private const val TAG = "UserRepository"
    }
}
