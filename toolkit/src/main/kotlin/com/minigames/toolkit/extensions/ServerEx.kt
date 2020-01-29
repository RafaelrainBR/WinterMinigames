package com.minigames.toolkit.extensions

import com.minigames.toolkit.dsl.module.plugin
import org.bukkit.Bukkit
import org.bukkit.event.Listener

val scheduler = Bukkit.getScheduler()

fun plugin.registerListener(vararg listeners: Listener) =
    listeners.forEach {
        Bukkit.getPluginManager().registerEvents(it, this)
    }