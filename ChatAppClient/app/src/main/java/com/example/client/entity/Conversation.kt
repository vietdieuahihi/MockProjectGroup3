package com.example.server.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Conversation(
    @PrimaryKey(autoGenerate = true) val conversationId: Int = 0,
    val senderId: Int,
    val receiverId: Int,
    val lastMessage: String,
    val timestamp: String,

    val timeDeleteSender: Long = -1,
    val timeDeleteReceiver: Long = -1
) : Parcelable, Serializable