package com.example.client.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.client.internal.SessionManager
import com.example.client.ui.adapter.ConversationAdapter
import com.example.client.utils.KEY_CONVERSATION
import com.example.client.viewmodel.ChatViewModel
import com.example.client.viewmodel.ConversationViewModel
import com.example.client.viewmodel.UserViewModel
import com.example.server.entity.Conversation
import com.example.server.entity.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListConversationFragment : Fragment() {

    private var _binding: FragmentListConversationBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels()
    private val conversationViewModel: ConversationViewModel by viewModels()
    private val chatViewModel: ChatViewModel by viewModels()

    private lateinit var conversationAdapter: ConversationAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var avatarButton: ImageView

    private val sessionManager: SessionManager by lazy { SessionManager.getIns() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel.initService((requireActivity() as MainActivity).messageService)
        conversationViewModel.initService((requireActivity() as MainActivity).messageService)
        chatViewModel.initService((requireActivity() as MainActivity).messageService)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListConversationBinding.inflate(inflater, container, false)
        setupViews()
        setupConversationRecyclerView()

        // Load default user
        loadCurrentUser()

        // Set click listener to open drawer when icon menu is clicked
        binding.icMenu.setOnClickListener {
            drawerLayout.openDrawer(binding.leftMenu)
        }

        return binding.root
    }

    private fun setupViews() {
        // Initialize DrawerLayout and Views
        drawerLayout = binding.drawerLayout
        avatarButton = binding.avatar

        binding.layoutSwitchUser.setOnClickListener {
            conversationAdapter.submitList(null)
            conversationAdapter.submitList(listOf())
            drawerLayout.close()
            findNavController().navigate(R.id.action_listConversationFragment_to_switchUserFragment)
        }
    }

    private fun setupConversationRecyclerView() {
        conversationAdapter = ConversationAdapter(onItemClick = { conversation ->
            navigateToDetail(conversation)
        }, onItemLongClick = {
            val selfId = userViewModel.currentUser.value?.userId
            val userId = if (it.senderId == selfId) it.receiverId else it.senderId
            userViewModel.fetchUserById(userId).let { user ->
                if (user == null) return@ConversationAdapter
                AlertDialog.Builder(requireContext()).setTitle(getString(R.string.confirm))
                    .setMessage("Are you sure you want to delete ${user.username} as friends?")
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->

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
                    }.setNegativeButton(getString(R.string.cancel), null).show()
            }

        }, userViewModel, chatViewModel, this)
        with(binding.userList) {
            setHasFixedSize(true)
            itemAnimator = null
            layoutManager = LinearLayoutManager(requireContext())
            adapter = conversationAdapter
        }
    }

    private fun loadCurrentUser() {
        userViewModel.fetchCurrentUser().observe(viewLifecycleOwner) { user ->
            sessionManager.currentUser = user
            updateUserInfo(user)
            fetchConversationsForUser(user)
        }
    }

    private fun fetchConversationsForUser(user: User) {
        conversationViewModel.fetchConversationsForUser(user.userId).observe(viewLifecycleOwner) { conversations ->
            conversationAdapter.submitList(null)
            conversationAdapter.submitList(listOf())
            conversationAdapter.submitList(conversations)
        }
    }

    private fun updateUserInfo(user: User) {
        Glide.with(this).load(user.avatar).placeholder(R.drawable.ic_avt).into(binding.avatar)
        Glide.with(this).load(user.avatar).placeholder(R.drawable.ic_avt).into(binding.imgConversation)
        binding.tvUsername.text = user.username
    }

    private fun navigateToDetail(conversation: Conversation) {
        findNavController().navigate(
            R.id.action_listConversationFragment_to_detailChatFragment,
            bundleOf(KEY_CONVERSATION to conversation)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
