package com.example.server

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.server.data.repository.ConversationRepository
import com.example.server.data.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var conversationRepository: ConversationRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.Main).launch {
            conversationRepository.getAllConversation().observe(this@MainActivity) {
                if (it == null) {
                    Log.d("VietDQ15", "getAllConversation is null")
                } else {
                    Log.d("VietDQ15", "getAllConversation is ${it.size}")
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.d(
                "VietDQ15",
                "conversationRepository.getConversationsForUser() ${conversationRepository.getConversationsForUser(1)}"
            )
        }
    }
}