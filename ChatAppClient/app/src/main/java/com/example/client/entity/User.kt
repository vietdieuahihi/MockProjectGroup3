package com.example.server.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class User(
    val userId: Int = 0,
    val username: String,
    val avatar: String,
    val flag: Int
) : Parcelable, Serializable