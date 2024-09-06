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
                val user1 = User(
                    userId = 1,
                    username = "Việt Siuuu",
                    avatar = "https://static01.nyt.com/images/2022/12/30/multimedia/30soccer-ronaldo-1-76fd/30soccer-ronaldo-1-76fd-mediumSquareAt3X.jpg",
                    flag = UserState.SELECTED.flag
                )
                userRepository.insertUser(user1).collect {
                    Log.d("MockData", "User 1 inserted: ${user1.username}")
                }

                val user2 = User(
                    userId = 2,
                    username = "Cô giáo Linh",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FCogiao.png?alt=media&token=21a478c1-519f-46d2-ab25-94d479a23298"
                )
                userRepository.insertUser(user2).collect {
                    Log.d("MockData", "User 2 inserted: ${user2.username}")
                }

                val user3 = User(
                    userId = 3,
                    username = "Dũng Noob",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FDunggg.png?alt=media&token=9afc3e0f-cff5-4d96-ab67-ee9d84e3047e"
                )
                userRepository.insertUser(user3).collect {
                    Log.d("MockData", "User 3 inserted: ${user3.username}")
                }

                val user4 = User(
                    userId = 4,
                    username = "Hoàng Vu Anh Chũ",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FHCAVVV.png?alt=media&token=1dcb0252-faa0-482e-aedf-09ea6cfc9f3f"
                )
                userRepository.insertUser(user4).collect {
                    Log.d("MockData", "User 4 inserted: ${user4.username}")
                }

                val user5 = User(
                    userId = 5,
                    username = "HTML CSS",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FHTML.png?alt=media&token=ecca8d55-7af9-4428-a494-e6e730009713"
                )
                userRepository.insertUser(user5).collect {
                    Log.d("MockData", "User 5 inserted: ${user5.username}")
                }

                val user6 = User(
                    userId = 6,
                    username = "Hoàng Noob",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FHoangCho.png?alt=media&token=27270ce1-b5ad-4a40-9623-f90709fc0241"
                )
                userRepository.insertUser(user6).collect {
                    Log.d("MockData", "User 6 inserted: ${user6.username}")
                }

                val user7 = User(
                    userId = 7,
                    username = "Em Lĩnh",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FLinhTK.png?alt=media&token=354d2121-4295-46f2-ba73-ea8956d2f0c1"
                )
                userRepository.insertUser(user7).collect {
                    Log.d("MockData", "User 7 inserted: ${user7.username}")
                }

                val user8 = User(
                    userId = 8,
                    username = "Anh Long",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FLong.png?alt=media&token=ccaee95c-4721-44a4-9cf8-f7dc8f998c12"
                )
                userRepository.insertUser(user8).collect {
                    Log.d("MockData", "User 8 inserted: ${user8.username}")
                }

                val user9 = User(
                    userId = 9,
                    username = "A An Nguyến",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FaAn.png?alt=media&token=8f48ef54-84b0-43fc-bf7d-4d62bd699a0d"
                )
                userRepository.insertUser(user9).collect {
                    Log.d("MockData", "User 9 inserted: ${user9.username}")
                }

                val user10 = User(
                    userId = 10,
                    username = "A Duy 2 ka",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FaDuy.png?alt=media&token=b8f22655-2607-42e6-ada7-46a2f61341e6"
                )
                userRepository.insertUser(user10).collect {
                    Log.d("MockData", "User 10 inserted: ${user10.username}")
                }

                val user11 = User(
                    userId = 11,
                    username = "A Huy Tiktoker",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FaHuyyy.png?alt=media&token=d2fa51b0-ff8a-437a-be3d-5def8785156a"
                )
                userRepository.insertUser(user11).collect {
                    Log.d("MockData", "User 11 inserted: ${user11.username}")
                }

                val user12 = User(
                    userId = 12,
                    username = "A Tuấn đồi chè",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FaTuan.png?alt=media&token=94bb23a0-349c-46f8-a6d4-2ceecf2ba8c1"
                )
                userRepository.insertUser(user12).collect {
                    Log.d("MockData", "User 12 inserted: ${user12.username}")
                }

                val user13 = User(
                    userId = 13,
                    username = "E Trung Nobita",
                    avatar = "https://firebasestorage.googleapis.com/v0/b/music-data-ae293.appspot.com/o/member_images%2FtrungNobita.jpg?alt=media&token=d83141f5-7954-4f78-8753-d2fcd103e940"
                )
                userRepository.insertUser(user13).collect {
                    Log.d("MockData", "User 13 inserted: ${user13.username}")
                }

                val conversation12 = Conversation(senderId = 1, receiverId = 2, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation12).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation12.conversationId}") }

                val conversation13 = Conversation(senderId = 1, receiverId = 3, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation13).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation13.conversationId}") }

                val conversation14 = Conversation(senderId = 1, receiverId = 4, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation14).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation14.conversationId}") }

                val conversation15 = Conversation(senderId = 1, receiverId = 5, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation15).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation15.conversationId}") }

                val conversation16 = Conversation(senderId = 1, receiverId = 6, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation16).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation16.conversationId}") }

                val conversation17 = Conversation(senderId = 1, receiverId = 7, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation17).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation17.conversationId}") }

                val conversation18 = Conversation(senderId = 1, receiverId = 8, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation18).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation18.conversationId}") }

                val conversation19 = Conversation(senderId = 1, receiverId = 9, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation19).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation19.conversationId}") }

                val conversation110 = Conversation(senderId = 1, receiverId = 10, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation110).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation110.conversationId}") }

                val conversation111 = Conversation(senderId = 1, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation111).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation111.conversationId}") }

                val conversation112 = Conversation(senderId = 1, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation112).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation112.conversationId}") }

                val conversation113 = Conversation(senderId = 1, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation113).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation113.conversationId}") }

                val conversation23 = Conversation(senderId = 2, receiverId = 3, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation23).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation23.conversationId}") }

                val conversation24 = Conversation(senderId = 2, receiverId = 4, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation24).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation24.conversationId}") }

                val conversation25 = Conversation(senderId = 2, receiverId = 5, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation25).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation25.conversationId}") }

                val conversation26 = Conversation(senderId = 2, receiverId = 6, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation26).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation26.conversationId}") }

                val conversation27 = Conversation(senderId = 2, receiverId = 7, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation27).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation27.conversationId}") }

                val conversation28 = Conversation(senderId = 2, receiverId = 8, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation28).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation28.conversationId}") }

                val conversation29 = Conversation(senderId = 2, receiverId = 9, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation29).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation29.conversationId}") }

                val conversation210 = Conversation(senderId = 2, receiverId = 10, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation210).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation210.conversationId}") }

                val conversation211 = Conversation(senderId = 2, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation211).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation211.conversationId}") }

                val conversation212 = Conversation(senderId = 2, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation212).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation212.conversationId}") }

                val conversation213 = Conversation(senderId = 2, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation213).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation213.conversationId}") }

                val conversation34 = Conversation(senderId = 3, receiverId = 4, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation34).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation34.conversationId}") }

                val conversation35 = Conversation(senderId = 3, receiverId = 5, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation35).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation35.conversationId}") }

                val conversation36 = Conversation(senderId = 3, receiverId = 6, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation36).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation36.conversationId}") }

                val conversation37 = Conversation(senderId = 3, receiverId = 7, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation37).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation37.conversationId}") }

                val conversation38 = Conversation(senderId = 3, receiverId = 8, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation38).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation38.conversationId}") }

                val conversation39 = Conversation(senderId = 3, receiverId = 9, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation39).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation39.conversationId}") }

                val conversation310 = Conversation(senderId = 3, receiverId = 10, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation310).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation310.conversationId}") }

                val conversation311 = Conversation(senderId = 3, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation311).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation311.conversationId}") }

                val conversation312 = Conversation(senderId = 3, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation312).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation312.conversationId}") }

                val conversation313 = Conversation(senderId = 3, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation313).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation313.conversationId}") }

                val conversation45 = Conversation(senderId = 4, receiverId = 5, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation45).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation45.conversationId}") }

                val conversation46 = Conversation(senderId = 4, receiverId = 6, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation46).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation46.conversationId}") }

                val conversation47 = Conversation(senderId = 4, receiverId = 7, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation47).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation47.conversationId}") }

                val conversation48 = Conversation(senderId = 4, receiverId = 8, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation48).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation48.conversationId}") }

                val conversation49 = Conversation(senderId = 4, receiverId = 9, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation49).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation49.conversationId}") }

                val conversation410 = Conversation(senderId = 4, receiverId = 10, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation410).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation410.conversationId}") }

                val conversation411 = Conversation(senderId = 4, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation411).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation411.conversationId}") }

                val conversation412 = Conversation(senderId = 4, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation412).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation412.conversationId}") }

                val conversation413 = Conversation(senderId = 4, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation413).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation413.conversationId}") }

                val conversation56 = Conversation(senderId = 5, receiverId = 6, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation56).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation56.conversationId}") }

                val conversation57 = Conversation(senderId = 5, receiverId = 7, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation57).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation57.conversationId}") }

                val conversation58 = Conversation(senderId = 5, receiverId = 8, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation58).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation58.conversationId}") }

                val conversation59 = Conversation(senderId = 5, receiverId = 9, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation59).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation59.conversationId}") }

                val conversation510 = Conversation(senderId = 5, receiverId = 10, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation510).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation510.conversationId}") }

                val conversation511 = Conversation(senderId = 5, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation511).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation511.conversationId}") }

                val conversation512 = Conversation(senderId = 5, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation512).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation512.conversationId}") }

                val conversation513 = Conversation(senderId = 5, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation513).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation513.conversationId}") }

                val conversation67 = Conversation(senderId = 6, receiverId = 7, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation67).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation67.conversationId}") }

                val conversation68 = Conversation(senderId = 6, receiverId = 8, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation68).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation68.conversationId}") }

                val conversation69 = Conversation(senderId = 6, receiverId = 9, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation69).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation69.conversationId}") }

                val conversation610 = Conversation(senderId = 6, receiverId = 10, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation610).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation610.conversationId}") }

                val conversation611 = Conversation(senderId = 6, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation611).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation611.conversationId}") }

                val conversation612 = Conversation(senderId = 6, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation612).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation612.conversationId}") }

                val conversation613 = Conversation(senderId = 6, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation613).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation613.conversationId}") }

                val conversation78 = Conversation(senderId = 7, receiverId = 8, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation78).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation78.conversationId}") }

                val conversation79 = Conversation(senderId = 7, receiverId = 9, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation79).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation79.conversationId}") }

                val conversation710 = Conversation(senderId = 7, receiverId = 10, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation710).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation710.conversationId}") }

                val conversation711 = Conversation(senderId = 7, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation711).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation711.conversationId}") }

                val conversation712 = Conversation(senderId = 7, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation712).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation712.conversationId}") }

                val conversation713 = Conversation(senderId = 7, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation713).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation713.conversationId}") }

                val conversation89 = Conversation(senderId = 8, receiverId = 9, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation89).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation89.conversationId}") }

                val conversation810 = Conversation(senderId = 8, receiverId = 10, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation810).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation810.conversationId}") }

                val conversation811 = Conversation(senderId = 8, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation811).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation811.conversationId}") }

                val conversation812 = Conversation(senderId = 8, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation812).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation812.conversationId}") }

                val conversation813 = Conversation(senderId = 8, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation813).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation813.conversationId}") }

                val conversation910 = Conversation(senderId = 9, receiverId = 10, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation910).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation910.conversationId}") }

                val conversation911 = Conversation(senderId = 9, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation911).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation911.conversationId}") }

                val conversation912 = Conversation(senderId = 9, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation912).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation912.conversationId}") }

                val conversation913 = Conversation(senderId = 9, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation913).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation913.conversationId}") }

                val conversation1011 = Conversation(senderId = 10, receiverId = 11, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation1011).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation1011.conversationId}") }

                val conversation1012 = Conversation(senderId = 10, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation1012).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation1012.conversationId}") }

                val conversation1013 = Conversation(senderId = 10, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation1013).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation1013.conversationId}") }

                val conversation1112 = Conversation(senderId = 11, receiverId = 12, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation1112).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation1112.conversationId}") }

                val conversation1113 = Conversation(senderId = 11, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation1113).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation1113.conversationId}") }

                val conversation1213 = Conversation(senderId = 12, receiverId = 13, lastMessage = "", timestamp = System.currentTimeMillis().toString())
                conversationRepository.insertConversation(conversation1213).collect { Log.d("MockData", "Conversation inserted with ID: ${conversation1213.conversationId}") }


            } catch (e: Exception) {
                Log.e("MockData", "Error inserting mock data: ${e.message}", e)
            }
        }
    }
}
