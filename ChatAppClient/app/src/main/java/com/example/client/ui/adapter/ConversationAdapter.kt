package com.example.client.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.client.R
import com.example.client.databinding.ItemConversationBinding
import com.example.client.utils.toTime
import com.example.client.viewmodel.ChatViewModel
import com.example.client.viewmodel.UserViewModel
import com.example.server.entity.Conversation

class ConversationAdapter(
    private val onItemClick: (Conversation) -> Unit,
    private val onItemLongClick: (Conversation) -> Unit,
    private val userViewModel: UserViewModel,
    private val chatViewModel: ChatViewModel,
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Conversation, ConversationAdapter.ConversationViewHolder>(ConversationDiffCallback()) {

    inner class ConversationViewHolder(private val binding: ItemConversationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(conversation: Conversation) {
            // Fetch User object based on receiverId
            val selfId = userViewModel.currentUser.value?.userId

            val userId = if (conversation.senderId == selfId) conversation.receiverId else conversation.senderId

            userViewModel.fetchUserById(userId).let { user ->
                binding.tvUsername.text = user?.username ?: binding.root.context.getString(R.string.unknown_user)
                Glide
                    .with(binding.imgConversation)
                    .load(user?.avatar ?: "")
                    .placeholder(R.drawable.baseline_person_24)
                    .into(binding.imgConversation)
            }

            val timeDelete = if (selfId == conversation.senderId) {
                conversation.timeDeleteSender
            } else {
                conversation.timeDeleteReceiver
            }

            if (timeDelete == -1L) {
                binding.tvLastMessage.isInvisible = conversation.lastMessage.isEmpty()
                binding.tvTimestamp.isInvisible = conversation.lastMessage.isEmpty()
            } else {
                if (timeDelete < conversation.timestamp.toLong()) {
                    binding.tvLastMessage.isInvisible = conversation.lastMessage.isEmpty()
                    binding.tvTimestamp.isInvisible = conversation.lastMessage.isEmpty()
                } else {
                    binding.tvLastMessage.isInvisible = true
                    binding.tvTimestamp.isInvisible = true
                }
            }

            conversation.lastMessageId?.let {
                val lastChat = chatViewModel.getChat(it)
                lastChat?.let { chat ->
                    if (chat.senderId != selfId) {
                        binding.tvLastMessage.text = conversation.lastMessage
                    } else if (chat.flag == 1) {
                        binding.tvLastMessage.text = conversation.lastMessage
                    } else {
                        binding.tvLastMessage.text = binding.root.context.getString(R.string.deleted_the_message)
                    }
                } ?: kotlin.run {
                    binding.tvLastMessage.text = conversation.lastMessage
                }
            } ?: kotlin.run {
                binding.tvLastMessage.text = conversation.lastMessage
            }

            binding.tvTimestamp.text = conversation.timestamp.toLong().toTime()
            binding.root.setOnClickListener {
                onItemClick(conversation)
            }
            binding.root.setOnLongClickListener {
                onItemLongClick.invoke(conversation)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val binding = ItemConversationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ConversationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ConversationDiffCallback : DiffUtil.ItemCallback<Conversation>() {
    override fun areItemsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
        return false
    }
}
