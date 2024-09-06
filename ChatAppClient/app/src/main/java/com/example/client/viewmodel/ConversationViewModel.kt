package com.example.client.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.client.data.repository.AppRepository
import com.example.server.IMessageService
import com.example.server.entity.Conversation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val repository: AppRepository = AppRepository(application.applicationContext)

    fun initService(messageService: IMessageService) {
        repository.initService(messageService)
    }

    fun fetchConversationsForUser(userId: Int): LiveData<List<Conversation>> {
//        CoroutineScope(Dispatchers.IO).launch {
//            repository.fetchConversationsForUser(userId)
//            withContext(Dispatchers.Main) {
//                // _conversations.value = fetchedConversations
//            }
//        }

        repository.fetchConversationsForUser(userId)
        return repository.conversations
    }

    fun updateConversation(conversationId: Int, lastMessage: String, lastMessageId: Long, timestamp: String) {
        repository.updateConversation(conversationId, lastMessage, lastMessageId, timestamp)
    }

    fun updateConversation(conversationId: Int, timeDeleteSender: Long, timeDeleteReceiver: Long) {
        repository.updateConversationV2(conversationId, timeDeleteSender, timeDeleteReceiver)
    }
}