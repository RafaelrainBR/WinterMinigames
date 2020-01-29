package com.minigames.toolkit.dsl.module

import com.minigames.toolkit.dsl.command.BukkitCommand
import com.minigames.toolkit.dsl.command.Command
import com.minigames.toolkit.dsl.plugin.KotlinPlugin
import com.minigames.toolkit.extensions.registerListener
import org.bukkit.Bukkit
import org.bukkit.event.Listener

typealias plugin = KotlinPlugin
typealias listener = Listener

internal interface IModule {

    fun provide()

    val commands: List<Command>

    val listeners: List<Listener>
}

abstract class Module(
    val name: String,
    val plugin: plugin,
    val priority: ModulePriority = ModulePriority.NORMAL
) : IModule {

    override val commands = arrayListOf<Command>()

    override val listeners = arrayListOf<Listener>()

    fun command(name: String, commandBlock: BukkitCommand.() -> Unit) = Command(name, commandBlock)

    fun listener(vararg listeners: Listener) = listeners.forEach { plugin.registerListener(it) }

    fun log(text: String) = Bukkit.getConsoleSender().sendMessage("[$name] $text")
}

enum class ModulePriority {
    LOWER,
    NORMAL,
    HIGH;
}