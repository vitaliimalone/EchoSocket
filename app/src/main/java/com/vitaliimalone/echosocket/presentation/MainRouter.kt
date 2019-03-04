package com.vitaliimalone.echosocket.presentation

import com.vitaliimalone.echosocket.R
import com.vitaliimalone.echosocket.presentation.chat.ChatFragment
import com.vitaliimalone.echosocket.presentation.utils.replaceWithoutBackStack

class MainRouter(private val mainActivity: MainActivity) {
    fun navigateToChat() {
        mainActivity.replaceWithoutBackStack(R.id.mainContainer, ChatFragment.newInstance())
    }
}
