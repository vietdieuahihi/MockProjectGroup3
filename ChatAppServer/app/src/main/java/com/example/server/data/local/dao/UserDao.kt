package com.example.server.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.server.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("DELETE FROM users WHERE userid = :userId")
    fun deleteUserById(userId: Int)

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users")
    fun getRemoteAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE userid = :userId")
    fun getUserById(userId: Int): User?

    @Query("SELECT * FROM users WHERE flag = 1")
    fun getCurrentUser(): User?

    @Update
    fun updates(users: List<User>)
}
