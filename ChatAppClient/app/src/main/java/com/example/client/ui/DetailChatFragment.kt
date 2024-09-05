package com.example.client.ui

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.example.client.constants.KEY_CONVERSATION
import com.example.client.constants.KEY_USER
import com.example.client.databinding.FragmentDetailChatBinding
import com.example.client.ui.adapter.ChatAdapter
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
        if (selfUser.userid == conversation.senderId)
            conversation.receiverId
        else conversation.senderId
    }

    private val original: ArrayList<Chat> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel.initService((requireActivity() as MainActivity).messageService)
        conversationViewModel.initService((requireActivity() as MainActivity).messageService)
        chatViewModel.initService((requireActivity() as MainActivity).messageService)
    }

    private lateinit var adapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            (it.getSerializable(KEY_CONVERSATION) as? Conversation)?.let { data ->
                conversation = data
            }
            chatViewModel.getChatByConversationId(conversation.conversationId)
            (it.getSerializable(KEY_USER) as? User)?.let { data ->
                selfUser = data
            }

            if (!::conversation.isInitialized) {
                findNavController().popBackStack()
                return
            }
            userViewModel.fetchUserById(yourId).let { user ->
                if (user != null) {
                    binding.tvUsername.text = user.username // Use the username from User
                } else {
                    binding.tvUsername.setText(getString(R.string.unknown_user))
                }

                Glide.with(binding.imgConversation).load(user?.avatar ?: "")
                    .placeholder(R.drawable.baseline_person_24)
                    .into(binding.imgConversation)
            }
        }

        adapter = ChatAdapter(selfUser.userid, yourId, userViewModel)
        binding.rcvChat.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DetailChatFragment.adapter
        }
        chatViewModel.chatInConversation.observe(viewLifecycleOwner) {
            val timeDelete = if (selfUser.userid == conversation.senderId) {
                conversation.timeDeleteSender
            } else {
                conversation.timeDeleteReceiver
            }

            it.forEach {
                Log.d("VietDQ15", "$it")
            }
            val result =
                it.filter { chats -> chats.timestamp.isNotEmpty() && chats.timestamp.toLong() > timeDelete }
            original.addAll(result)
            adapter.submitList(result)

            Handler(Looper.getMainLooper()).postDelayed({
                binding.rcvChat.smoothScrollToPosition(
                    original.size
                )
            }, 200L)
        }
        binding.icSend.setOnClickListener {
            val message = binding.etInputMessage.text.toString().trim()
            if (message.isEmpty()) return@setOnClickListener

            val yourId = if (selfUser.userid == conversation.senderId)
                conversation.receiverId
            else conversation.senderId
            val chat = Chat(
                senderId = selfUser.userid,
                receiverId = yourId,
                message = message,
                timestamp = System.currentTimeMillis().toString(),
                conversationId = conversation.conversationId
            )
            original.add(chat)
            binding.etInputMessage.setText("")
            chatViewModel.sendMessage(chat)
            conversationViewModel.updateConversation(
                conversation.conversationId,
                message,
                chat.timestamp
            )
            adapter.submitList(original)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.rcvChat.smoothScrollToPosition(
                    original.size
                )
            }, 300L)
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
                    if (selfUser.userid == conversation.senderId) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
