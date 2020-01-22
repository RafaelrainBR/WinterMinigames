package com.minigames.toolkit.extensions

import org.bukkit.ChatColor

val String.color: String get() = ChatColor.translateAlternateColorCodes('&', this)
