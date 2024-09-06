package com.example.client.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.client.R
import com.example.client.databinding.ItemUserBinding
import com.example.server.entity.User

class UserAdapter(
    private val onItemClick: (User) -> Unit
) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                Glide
                    .with(imgConversation)
                    .load(user.avatar)
                    .placeholder(R.drawable.ic_avt)
                    .into(imgConversation)

                tvUsername.text = user.username
                ivSelected.isVisible = user.flag == 1
                root.setOnClickListener {
                    onItemClick(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId && oldItem.flag == newItem.flag
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem && oldItem.userId == newItem.userId && oldItem.flag == newItem.flag
    }
}
