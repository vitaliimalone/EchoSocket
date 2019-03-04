package com.vitaliimalone.echosocket.presentation.chat.common

import androidx.recyclerview.widget.DiffUtil
import com.vitaliimalone.echosocket.domain.model.Message

class MessagesDiffUtilCallback(
    private val old: List<Message>,
    private val new: List<Message>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size
    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].time == new[newItemPosition].time
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}
