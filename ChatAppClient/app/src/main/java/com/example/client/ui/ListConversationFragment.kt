package com.example.client.ui

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.client.MainActivity
import com.example.client.R
import com.example.client.databinding.FragmentListConversationBinding
import com.example.client.ui.adapter.ConversationAdapter
import com.example.client.viewmodel.ChatViewModel
import com.example.client.viewmodel.ConversationViewModel
import com.example.client.viewmodel.UserViewModel
import com.example.server.entity.Conversation
import com.example.server.entity.User

class ListConversationFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentListConversationBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels()
    private val conversationViewModel: ConversationViewModel by viewModels()
    private val chatViewModel: ChatViewModel by viewModels()

    private lateinit var conversationAdapter: ConversationAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var avatarButton: ImageView
    private var currentUser: User? = null
    private var users: List<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel.initService((requireActivity() as MainActivity).messageService)
        conversationViewModel.initService((requireActivity() as MainActivity).messageService)
        chatViewModel.initService((requireActivity() as MainActivity).messageService)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListConversationBinding.inflate(inflater, container, false)
        setupConversationRecyclerView()
        observeConversations()

        // Load default user
        loadCurrentUser()

        observeUsers()

        // Set click listener to open drawer when avatar is clicked
        avatarButton.setOnClickListener { drawerLayout.openDrawer(binding.leftMenu) }
        return binding.root
    }

    private fun observeUsers() {
        userViewModel.users.observe(viewLifecycleOwner) { users ->
            println("VietDQ15: observeUsers is call $users")
            this.users = users

//            with(binding.spUserList) {
//                if (adapter == null) {
//                    isSelected = false
//                    adapter = SpinnerUserAdapter(users)
//                    binding.spUserList.onItemSelectedListener = this@ListConversationFragment
//                    setSelection(users.indexOfFirst { it.flag == 1 }, false)
//                }
//            }
        }
    }


    private fun setupConversationRecyclerView() {
        conversationAdapter = ConversationAdapter(onItemClick = { conversation ->
            navigateToDetail(conversation)
        }, onItemLongClick = {
            val selfId = userViewModel.currentUser.value?.userid
            val userId = if (it.senderId == selfId) it.receiverId else it.senderId
            userViewModel.fetchUserById(userId).let { user ->
                if (user == null) return@ConversationAdapter
                AlertDialog.Builder(requireContext()).setTitle("Confirm")
                    .setMessage("Are you sure you want to delete ${user.username} as friends?")
                    .setPositiveButton("OK") { _, _ ->

                        var timeDeleteSender: Long = it.timeDeleteSender
                        var timeDeleteReceiver: Long = it.timeDeleteReceiver
                        if (selfId == it.senderId) {
                            timeDeleteSender = System.currentTimeMillis()
                        } else {
                            timeDeleteReceiver = System.currentTimeMillis()
                        }
                        conversationViewModel.updateConversation(
                            it.conversationId, timeDeleteSender, timeDeleteReceiver
                        )
                        userViewModel.fetchCurrentUser()
                    }.setNegativeButton("Cancel", null).show()
            }

        }, userViewModel, this)
        with(binding.userList) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = conversationAdapter
        }
    }

    private fun observeConversations() {
//        conversationViewModel.conversations.observe(viewLifecycleOwner) { conversations ->
//            conversationAdapter.submitList(conversations)
//        }
    }

    private fun loadCurrentUser() {
        println("VietDQ15: loadCurrentUser is call")
        userViewModel.fetchCurrentUser().observe(viewLifecycleOwner) { user ->
            println("VietDQ15: loadCurrentUser is call $user")
            currentUser = user
            userViewModel.currentUserV2 = user

            updateUserInfo(user)
//            // load all user
//            userViewModel.getUsers()
            fetchConversationsForUser(user)
        }
    }

    private fun fetchConversationsForUser(user: User) {
        println("VietDQ15: fetchConversationsForUser is call $user")
//        conversationAdapter.submitList(listOf())
        conversationViewModel.fetchConversationsForUser(user.userid).observe(viewLifecycleOwner) { conversations ->
            conversationAdapter.submitList(conversations)
            Handler(Looper.getMainLooper()).postDelayed({ conversationAdapter.submitList(conversations) }, 200L)
        }
    }

    private fun loadUser(userId: Int) {
        userViewModel.getUserById(userId).observe(viewLifecycleOwner) { user ->
            currentUser = user
            userViewModel.currentUserV2 = user
            updateUserInfo(user)
        }
    }

    private fun updateUserInfo(user: User) {
        Glide.with(this).load(user.avatar).placeholder(R.drawable.ic_avt).into(binding.avatar)
        Glide.with(this).load(user.avatar).placeholder(R.drawable.ic_avt).into(binding.imgConversation)
        binding.tvUsername.text = user.username
    }

    private fun switchUser(user: User) {
        loadUser(user.userid)
        drawerLayout.closeDrawer(binding.leftMenu)
        conversationViewModel.fetchConversationsForUser(user.userid)
    }

    private fun navigateToDetail(conversation: Conversation) {
        findNavController().navigate(
            R.id.action_listConversationFragment_to_detailChatFragment, bundleOf(
                "conversation" to conversation, "user" to userViewModel.currentUser.value
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        users?.let {
            val item = it[p2]
            Log.d(
                "VietDQ15",
                "item.userid = ${item.userid}, currentUser?.userid = ${currentUser?.userid}, item.userid == currentUser?.userid = ${item.userid == currentUser?.userid}"
            )
            if (item.userid == currentUser?.userid) return
            currentUser = item
            userViewModel.switchUser(item)
            drawerLayout.close()
            loadCurrentUser()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}
