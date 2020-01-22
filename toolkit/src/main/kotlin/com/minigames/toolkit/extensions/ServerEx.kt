package com.minigames.toolkit.extensions

import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin

fun Plugin.setJoinMessage(message: String) =
    inlineListener<PlayerJoinEvent> {
        joinMessage = message.color.replace("{player}", player.name)
    }

fun Plugin.setQuitMessage(message: String) =
    inlineListener<PlayerQuitEvent> {
        quitMessage = message.color.replace("{player}", player.name)
    }

inline fun <reified E : Event> Plugin.inlineListener(crossinline block: E.() -> Unit) {
    Bukkit.getPluginManager().registerEvents(
        object : Listener {
            @EventHandler
            fun onEvent(e: E) {
                block(e)
            }
        }, this
    )
}