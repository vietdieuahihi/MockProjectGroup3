package com.example.server.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) val userid: Int = 0,
    val username: String,
    val avatar: String,
    val flag: Int = UserState.NORMAL.flag
) : Parcelable

enum class UserState(val flag: Int) {
    SELECTED(1),
    NORMAL(0),
    DEACTIVATE(-1)
}
