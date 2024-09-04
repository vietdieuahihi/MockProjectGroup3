package com.example.server.data.mockdata

import android.util.Log
import com.example.server.data.repository.ChatRepository
import com.example.server.data.repository.ConversationRepository
import com.example.server.data.repository.UserRepository
import com.example.server.entity.Conversation
import com.example.server.entity.User
import com.example.server.entity.UserState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object MockData {
    fun insertMockData(
        userRepository: UserRepository,
        conversationRepository: ConversationRepository,
        chatRepository: ChatRepository
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Insert Users
                val user1 = User(
                    userid = 1,
                    username = "Việt Siuuu",
                    avatar = "https://static01.nyt.com/images/2022/12/30/multimedia/30soccer-ronaldo-1-76fd/30soccer-ronaldo-1-76fd-mediumSquareAt3X.jpg",
                    flag = UserState.SELECTED.flag
                )
                userRepository.insertUser(user1).collect {
                    Log.d("MockData", "User 1 inserted: ${user1.username}")
                }

                val user2 = User(
                    userid = 2,
                    username = "Hana Anh",
                    avatar = "https://5vape.net/wp-content/uploads/2023/01/67966839_918301265195950_7320953055191498752_n.jpg"
                )
                userRepository.insertUser(user2).collect {
                    Log.d("MockData", "User 2 inserted: ${user2.username}")
                }

                val user3 = User(
                    userid = 3,
                    username = "Vợ cũ Xemexis",
                    avatar = "https://kenh14cdn.com/203336854389633024/2021/7/16/x1-16264314762061661461728.jpg"
                )
                userRepository.insertUser(user3).collect {
                    Log.d("MockData", "User 3 inserted: ${user3.username}")
                }

                val user4 = User(
                    userid = 4,
                    username = "Hót",
                    avatar = "https://antimatter.vn/wp-content/uploads/2022/10/hinh-anh-gai-xinh-de-thuong.jpg"
                )
                userRepository.insertUser(user4).collect {
                    Log.d("MockData", "User 4 inserted: ${user4.username}")
                }

                val conversation12 = Conversation(
                    senderId = 1,
                    receiverId = 2,
                    lastMessage = "",
                    timestamp = System.currentTimeMillis().toString()
                )
                conversationRepository.insertConversation(conversation12).collect {
                    Log.d("MockData", "Conversation inserted with ID: ${conversation12.conversationId}")
                }
                val conversation13 = Conversation(
                    senderId = 1,
                    receiverId = 3,
                    lastMessage = "",
                    timestamp = System.currentTimeMillis().toString()
                )
                conversationRepository.insertConversation(conversation13).collect {
                    Log.d("MockData", "Conversation inserted with ID: ${conversation13.conversationId}")
                }
                val conversation14 = Conversation(
                    senderId = 1,
                    receiverId = 4,
                    lastMessage = "",
                    timestamp = System.currentTimeMillis().toString()
                )
                conversationRepository.insertConversation(conversation14).collect {
                    Log.d("MockData", "Conversation inserted with ID: ${conversation14.conversationId}")
                }
                val conversation23 = Conversation(
                    senderId = 2,
                    receiverId = 3,
                    lastMessage = "",
                    timestamp = System.currentTimeMillis().toString()
                )
                conversationRepository.insertConversation(conversation23).collect {
                    Log.d("MockData", "Conversation inserted with ID: ${conversation23.conversationId}")
                }
                val conversation24 = Conversation(
                    senderId = 2,
                    receiverId = 4,
                    lastMessage = "",
                    timestamp = System.currentTimeMillis().toString()
                )
                conversationRepository.insertConversation(conversation24).collect {
                    Log.d("MockData", "Conversation inserted with ID: ${conversation24.conversationId}")
                }
                val conversation34 = Conversation(
                    senderId = 3,
                    receiverId = 4,
                    lastMessage = "",
                    timestamp = System.currentTimeMillis().toString()
                )
                conversationRepository.insertConversation(conversation34).collect {
                    Log.d("MockData", "Conversation inserted with ID: ${conversation34.conversationId}")
                }
            } catch (e: Exception) {
                Log.e("MockData", "Error inserting mock data: ${e.message}", e)
            }
        }
    }
}
