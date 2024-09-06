package com.example.client.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.client.data.repository.AppRepository
import com.example.server.IMessageService
import com.example.server.entity.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val repository: AppRepository = AppRepository(application.applicationContext)

    private val _chatInConversation = MediatorLiveData<List<Chat>>()
    val chatInConversation: LiveData<List<Chat>> get() = _chatInConversation

    fun initService(messageService: IMessageService) {
        repository.initService(messageService)
    }

    fun getChatByConversationId(conversationId: Int) {
        viewModelScope.launch {
            _chatInConversation.value = repository.getAllChatsByConversationId(conversationId)
        }
    }

    fun sendMessage(chat: Chat) {
        viewModelScope.launch {
            repository.sendMessage(chat)
        }
    }

    fun hideChat(chatId: Long) {
        viewModelScope.launch {
            repository.hideChat(chatId)
        }
    }

    fun getChat(chatId: Long) = repository.getChatById(chatId)
}