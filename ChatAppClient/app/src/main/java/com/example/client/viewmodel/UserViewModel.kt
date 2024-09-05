package com.example.client.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.client.data.repository.AppRepository
import com.example.server.IMessageService
import com.example.server.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val repository: AppRepository = AppRepository(application.applicationContext)

    val users: LiveData<List<User>> = repository.users
    val currentUser: LiveData<User> get() = repository.currentUser

    var currentUserV2: User? = null

    init {
        users.observeForever { userList ->
            // Set default user to the one with id = 1 if available
            repository.getUserById(
                userList.find { it.userid == 1 }?.userid ?: userList.firstOrNull()?.userid ?: 1
            )
        }
    }

    fun initService(messageService: IMessageService) {
        repository.initService(messageService)
    }

    fun getUserById(userId: Int): LiveData<User> {
        // Sử dụng repository để lấy User và trả về LiveData<User>
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
        println("VietDQ15: switchUser is call $user")
        repository.switchUser(user.userid)
    }
}
