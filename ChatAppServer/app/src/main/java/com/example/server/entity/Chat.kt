package com.example.server.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "chats",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userid"], childColumns = ["senderId"]),
        ForeignKey(entity = User::class, parentColumns = ["userid"], childColumns = ["receiverId"]),
        ForeignKey(entity = Conversation::class, parentColumns = ["conversationId"], childColumns = ["conversationId"])
    ]
)
@Parcelize
data class Chat(
    @PrimaryKey(autoGenerate = true) val chatId: Int = 0,
    val senderId: Int,
    val receiverId: Int,
    val message: String,
    val timestamp: String,
    val conversationId: Int
) : Parcelable