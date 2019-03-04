package com.vitaliimalone.echosocket.presentation.chat.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.echosocket.R
import com.vitaliimalone.echosocket.databinding.ListItemMessageReceivedBinding
import com.vitaliimalone.echosocket.databinding.ListItemMessageSentBinding
import com.vitaliimalone.echosocket.domain.model.Message
import com.vitaliimalone.echosocket.presentation.base.BindingViewHolder
import com.vitaliimalone.echosocket.presentation.chat.ChatViewModel

class MessagesAdapter(
        private val viewModel: ChatViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var messages: List<Message> = emptyList()
        set(value) {
            DiffUtil.calculateDiff(MessagesDiffUtilCallback(field, value)).dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Message.MessageType.SENT.ordinal -> {
                val binding = DataBindingUtil.inflate<ListItemMessageSentBinding>(inflater,
                        R.layout.list_item_message_sent, parent, false)
                MessageSentViewHolder(binding)
            }
            Message.MessageType.RECEIVED.ordinal -> {
                val binding = DataBindingUtil.inflate<ListItemMessageReceivedBinding>(inflater,
                        R.layout.list_item_message_received, parent, false)
                MessageReceivedViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        return messages[position].type.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageSentViewHolder -> {
                holder.bind(messages[position], viewModel)
            }
            is MessageReceivedViewHolder -> {
                holder.bind(messages[position], viewModel)
            }
            else -> throw IllegalArgumentException()
        }
    }

    class MessageSentViewHolder(
            private val binding: ListItemMessageSentBinding
    ) : BindingViewHolder<Message, ChatViewModel>(binding.root) {
        override fun bind(item: Message, viewModel: ChatViewModel) {
            binding.message = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    class MessageReceivedViewHolder(
            private val binding: ListItemMessageReceivedBinding
    ) : BindingViewHolder<Message, ChatViewModel>(binding.root) {
        override fun bind(item: Message, viewModel: ChatViewModel) {
            binding.message = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}
