package com.example.mockserver.data.repository

import androidx.lifecycle.LiveData
import com.example.server.data.local.dao.UserDao
import com.example.server.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {
    fun insertUser(user: User) : Flow<Unit> = flow {
        emit(userDao.insertUser(user))
    }.flowOn(Dispatchers.IO)

    fun deleteUserById(userId: Long): Flow<Unit> = flow {
        emit(userDao.deleteUserById(userId))
    }.flowOn(Dispatchers.IO)

    fun getUserById(userId: Long): LiveData<User> = userDao.getUserById(userId)

    fun getAllUsers(): LiveData<List<User>> = userDao.getAllUsers()

}