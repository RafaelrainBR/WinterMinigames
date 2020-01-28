package com.minigames.util

import com.minigames.toolkit.dsl.plugin.KotlinPlugin
import com.minigames.util.chat.Chat

class Util : KotlinPlugin() {

    override fun onStart() {
        registerModules(
            Chat(this)
        )
    }
}