package com.minigames.util.chat

import com.minigames.toolkit.dsl.module.Module
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.plugin.Plugin

class Chat(plugin: Plugin) : Module("Chat", plugin) {

    override fun provide() {

        listener<AsyncPlayerChatEvent> {
            format = "§7[Membro]§r${player.name}§7 » $message"
        }

    }


}