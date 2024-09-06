package com.example.client.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.client.R
import com.example.client.databinding.ItemReceivedMessageBinding
import com.example.client.databinding.ItemSentMessageBinding
import com.example.client.utils.toTimeV2
import com.example.client.viewmodel.UserViewModel
import com.example.server.entity.Chat

class ChatAdapter(
    private val selfId: Int,
    private val yourId: Int,
    private val userViewModel: UserViewModel,
    private val onLongClickItemListener: ((Chat) -> Unit)? = null
) : ListAdapter<Chat, RecyclerView.ViewHolder>(ChatDiffCallback()) {

    inner class ChatViewHolder(private val binding: ItemSentMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat) {
            binding.textMessage.text = chat.message
            binding.textTimestamp.text = chat.timestamp.toLong().toTimeV2()

            binding.textMessage.setOnLongClickListener {
                onLongClickItemListener?.invoke(chat)
                false
            }

            binding.textMessage.updateLayoutParams {
                width = 0
            }
        }
    }

    inner class ChatViewHolderV2(private val binding: ItemReceivedMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat) {
            userViewModel.fetchUserById(yourId).let { user ->
                Glide.with(binding.imgConversation).load(user?.avatar ?: "").placeholder(R.drawable.baseline_person_24)
                    .into(binding.imgConversation)
            }
            binding.textMessage.text = chat.message
            binding.textTimestamp.text = chat.timestamp.toLong().toTimeV2()
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (selfId == getItem(position).senderId) return 1
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val binding = ItemSentMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ChatViewHolder(binding)
        }
        val binding = ItemReceivedMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatViewHolderV2(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as ChatViewHolder).bind(getItem(position))
        } else {
            (holder as ChatViewHolderV2).bind(getItem(position))
        }
    }
}


class ChatDiffCallback : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }
}