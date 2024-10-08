package com.example.server.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(
    tableName = "conversations",
    indices = [
        Index(value = ["senderId"]),
        Index(value = ["receiverId"])],
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["senderId"]),
        ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["receiverId"]),
        ForeignKey(entity = Chat::class, parentColumns = ["chatId"], childColumns = ["lastMessageId"])
    ]
)
@Parcelize
data class Conversation(
    @PrimaryKey(autoGenerate = true) val conversationId: Int = 0,
    val senderId: Int,
    val receiverId: Int,
    val lastMessage: String,
    val lastMessageId: Long? = null,
    val timestamp: String,
    val timeDeleteSender: Long = -1,
    val timeDeleteReceiver: Long = -1
) : Parcelable
