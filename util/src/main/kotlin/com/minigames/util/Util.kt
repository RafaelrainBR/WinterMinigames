package com.minigames.util

import com.minigames.toolkit.dsl.plugin.KotlinPlugin
import com.minigames.toolkit.extensions.setJoinMessage
import com.minigames.toolkit.extensions.setQuitMessage
import com.minigames.util.chat.Chat

class Util : KotlinPlugin() {

    override fun onStart() {
        setJoinMessage("&e{player} entrou na partida.")
        setQuitMessage("&e{player} saiu da partida.")
        registerModules(
            Chat(this)
        )
    }
}