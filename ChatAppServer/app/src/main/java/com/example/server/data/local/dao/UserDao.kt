package com.example.server.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.server.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("DELETE FROM users WHERE userid = :userId")
    fun deleteUserById(userId: Long)

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users")
    fun getRemoteAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE userid = :userId")
    fun getUserById(userId: Long): LiveData<User>

}
