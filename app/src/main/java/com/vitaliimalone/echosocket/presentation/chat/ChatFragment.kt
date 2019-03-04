package com.vitaliimalone.echosocket.presentation.chat

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitaliimalone.echosocket.databinding.ChatFragmentBinding
import com.vitaliimalone.echosocket.presentation.base.BaseFragment
import com.vitaliimalone.echosocket.presentation.chat.common.MessagesAdapter
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<ChatFragmentBinding>(com.vitaliimalone.echosocket.R.layout.chat_fragment) {
    companion object {
        fun newInstance(): ChatFragment {
            val args = Bundle()
            val fragment = ChatFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val viewModel: ChatViewModel by viewModel()
    private val messagesAdapter by lazy { MessagesAdapter(viewModel) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            viewModel = this@ChatFragment.viewModel
            messagesRecyclerView.adapter = messagesAdapter
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.stackFromEnd = true
            messagesRecyclerView.layoutManager = layoutManager
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
        setupObservers()
        setupKeyboardListener()
    }

    private fun setupObservers() {
        viewModel.apply {
            messages.observe(viewLifecycleOwner, Observer {
                messagesAdapter.messages = it
                scrollToBottom()
            })
        }
    }

    private fun setupKeyboardListener() {
        KeyboardVisibilityEvent.setEventListener(requireActivity()) {
            if (it) scrollToBottom()
        }
    }

    private fun scrollToBottom() {
        binding.messagesRecyclerView.scrollToPosition(messagesAdapter.messages.lastIndex)
    }
}
