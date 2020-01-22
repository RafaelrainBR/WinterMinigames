package com.minigames.toolkit.dsl.module

import com.minigames.toolkit.dsl.command.BukkitCommand
import com.minigames.toolkit.dsl.command.Command
import com.minigames.toolkit.extensions.inlineListener
import org.bukkit.event.Event
import org.bukkit.plugin.Plugin


interface IModule {

    fun provide()

    val commands: List<Command>
}

abstract class Module(
    val name: String,
    val plugin: Plugin,
    val priority: ModulePriority = ModulePriority.NORMAL
) : IModule {

    override val commands = arrayListOf<Command>()

    fun command(name: String, commandBlock: BukkitCommand.() -> Unit) = Command(name, commandBlock).run(commands::add)

    inline fun <reified E : Event> listener(crossinline block: (E).() -> Unit) = plugin.inlineListener(block)
}

enum class ModulePriority {
    LOWER,
    NORMAL,
    HIGH;
}