package com.example.server.data.repository

import com.example.server.data.local.dao.UserDao
import com.example.server.entity.User
import com.example.server.entity.UserState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: User): Flow<Unit> = flow {
        emit(userDao.insertUser(user))
    }.flowOn(Dispatchers.IO)

    suspend fun deleteUserById(userId: Int): Flow<Unit> = flow {
        emit(userDao.deleteUserById(userId))
    }.flowOn(Dispatchers.IO)

    suspend fun getUserById(userId: Int): User? = userDao.getUserById(userId)

    suspend fun getCurrentUser(): User? = userDao.getCurrentUser()

    suspend fun getRemoteAllUsers(): List<User> = userDao.getRemoteAllUsers()

    suspend fun switchUser(userId: Int) {
        val users = arrayListOf<User>()
        getRemoteAllUsers().forEach {
            val flag = if (it.userId == userId) UserState.SELECTED.flag else UserState.NORMAL.flag
            users.add(User(it.userId, it.username, it.avatar, flag))
        }
        userDao.updates(users)
    }
}