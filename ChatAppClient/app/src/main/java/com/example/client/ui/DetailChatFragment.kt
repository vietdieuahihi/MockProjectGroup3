package com.example.client.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.client.MainActivity
import com.example.client.R
import com.example.client.databinding.FragmentDetailChatBinding
import com.example.client.internal.SessionManager
import com.example.client.ui.adapter.ChatAdapter
import com.example.client.utils.KEY_CONVERSATION
import com.example.client.viewmodel.ChatViewModel
import com.example.client.viewmodel.ConversationViewModel
import com.example.client.viewmodel.UserViewModel
import com.example.server.entity.Chat
import com.example.server.entity.Conversation
import com.example.server.entity.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailChatFragment : Fragment() {

    private var _binding: FragmentDetailChatBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels()
    private val conversationViewModel: ConversationViewModel by viewModels()
    private val chatViewModel: ChatViewModel by viewModels()

    private lateinit var conversation: Conversation
    private lateinit var selfUser: User
    private val yourId: Int by lazy {
        if (selfUser.userId == conversation.senderId) conversation.receiverId else conversation.senderId
    }
    private val original: ArrayList<Chat> = arrayListOf()
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel.initService((requireActivity() as MainActivity).messageService)
        conversationViewModel.initService((requireActivity() as MainActivity).messageService)
        chatViewModel.initService((requireActivity() as MainActivity).messageService)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            (it.getSerializable(KEY_CONVERSATION) as? Conversation)?.let { data ->
                conversation = data
            }

            SessionManager.getIns().currentUser?.let { user -> selfUser = user }

            if (!::conversation.isInitialized || !::selfUser.isInitialized) {
                findNavController().popBackStack()
                return
            }
            // get data chats
            chatViewModel.getChatByConversationId(conversation.conversationId)
            // get data info yourUser
            userViewModel.fetchUserById(yourId).let { user ->
                binding.tvUsername.text =
                    user?.username ?: requireContext().getString(R.string.unknown_user)
                Glide.with(binding.imgConversation).load(user?.avatar ?: "")
                    .placeholder(R.drawable.baseline_person_24)
                    .into(binding.imgConversation)
            }
        }

//        adapter = ChatAdapter(selfUser.userid, yourId, userViewModel)
        adapter = ChatAdapter(selfUser.userId, yourId, userViewModel) { chat ->
            AlertDialog.Builder(requireContext()).setTitle("Confirm")
                .setMessage("Are you sure you want to delete the message?")
                .setPositiveButton("OK") { _, _ ->
                    chatViewModel.hideChat(chat.chatId)
                    original.removeIf { it.chatId == chat.chatId }
                    adapter.notifyDataSetChanged()
                    scrollToEnd()
                }.setNegativeButton("Cancel", null).show()
        }

        binding.rcvChat.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DetailChatFragment.adapter
            addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
                if (bottom < oldBottom) {
                    this.postDelayed({
                        scrollToEnd()
                    }, 100)
                }
            }
        }

        chatViewModel.chatInConversation.observe(viewLifecycleOwner) { it ->
            val timeDelete = if (selfUser.userId == conversation.senderId) {
                conversation.timeDeleteSender
            } else {
                conversation.timeDeleteReceiver
            }

            it.filter { chat ->
                chat.timestamp.isNotEmpty()
                        && chat.timestamp.toLong() > timeDelete
                        && ((chat.senderId == selfUser.userId && chat.flag == 1) || chat.senderId != selfUser.userId)
            }.also {
                original.addAll(it)
                adapter.submitList(it)
            }
            scrollToEnd()
        }
        binding.icSend.setOnClickListener {
            val message = binding.etInputMessage.text.toString().trim()
            if (message.isEmpty()) return@setOnClickListener

            val yourId = if (selfUser.userId == conversation.senderId)
                conversation.receiverId
            else conversation.senderId
            val now = System.currentTimeMillis()
            val chat = Chat(
                chatId = now,
                senderId = selfUser.userId,
                receiverId = yourId,
                message = message,
                timestamp = now.toString(),
                conversationId = conversation.conversationId
            )
            original.add(chat)
            binding.etInputMessage.setText("")

            chatViewModel.sendMessage(chat)
            conversationViewModel.updateConversation(
                conversation.conversationId,
                message,
                now,
                chat.timestamp
            )

            adapter.submitList(original)

            scrollToEnd()
        }

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.icDelete.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.confirm))
                .setMessage("Are you sure you want to delete ${binding.tvUsername.text} box chat?")
                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    var timeDeleteSender: Long = conversation.timeDeleteSender
                    var timeDeleteReceiver: Long = conversation.timeDeleteReceiver
                    if (selfUser.userId == conversation.senderId) {
                        timeDeleteSender = System.currentTimeMillis()
                    } else {
                        timeDeleteReceiver = System.currentTimeMillis()
                    }

                    conversationViewModel.updateConversation(
                        conversation.conversationId,
                        timeDeleteSender,
                        timeDeleteReceiver
                    )
                    findNavController().popBackStack()
                }.setNegativeButton(getString(R.string.cancel), null)
                .show()
        }
    }

    private fun scrollToEnd() {
        binding.rcvChat.postDelayed({
            binding.rcvChat.scrollToPosition(this@DetailChatFragment.adapter.itemCount - 1)
        }, 50L)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}