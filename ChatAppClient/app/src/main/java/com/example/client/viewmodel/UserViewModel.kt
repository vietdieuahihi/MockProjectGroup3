package com.example.client.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.client.data.repository.AppRepository
import com.example.server.IMessageService
import com.example.server.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val users: LiveData<List<User>> = repository.users
    val currentUser: LiveData<User> get() = repository.currentUser

    init {
        users.observeForever { userList ->
            repository.getUserById(
                userList.find { it.userId == 1 }?.userId ?: userList.firstOrNull()?.userId ?: 1
            )
        }
    }

    fun initService(messageService: IMessageService) {
        repository.initService(messageService)
    }

    fun getUserById(userId: Int): LiveData<User> {
        repository.getUserById(userId)
        return repository.userById
    }

    fun fetchUserById(userId: Int) = repository.fetchUserById(userId)

    fun fetchCurrentUser(): LiveData<User> {
        repository.fetchCurrentUser()
        return repository.currentUser
    }

    fun getUsers() {
        repository.fetchUsers()
    }

    fun switchUser(user: User) {
        Log.d("UserViewModel", "switchUser is called for $user")
        repository.switchUser(user.userId)
    }
}
