package com.minigames.util.chat

import com.minigames.toolkit.dsl.module.Module
import com.minigames.toolkit.dsl.module.listener
import com.minigames.toolkit.dsl.module.plugin
import org.bukkit.event.EventHandler
import org.bukkit.event.player.AsyncPlayerChatEvent

class Chat(plugin: plugin) : Module("Chat", plugin) {

    override fun provide() {
        listener(Listener)
    }

    object Listener : listener {
        @EventHandler
        fun onChat(e: AsyncPlayerChatEvent) {
            e.format = "${e.player.name} §7» ${e.message}"
        }
    }

    val command = command("tell") {

    }

}