package com.example.server.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "chats",
    indices = [
        Index(value = ["senderId"]),
        Index(value = ["receiverId"]),
        Index(value = ["conversationId"])
    ],
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["senderId"]),
        ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["receiverId"]),
        ForeignKey(entity = Conversation::class, parentColumns = ["conversationId"], childColumns = ["conversationId"])
    ]
)
@Parcelize
data class Chat(
    @PrimaryKey val chatId: Long,
    val senderId: Int,
    val receiverId: Int,
    val message: String,
    val timestamp: String,
    val conversationId: Int,
    val flag: Int = 1 // 1 is show, 0 is hide
) : Parcelable