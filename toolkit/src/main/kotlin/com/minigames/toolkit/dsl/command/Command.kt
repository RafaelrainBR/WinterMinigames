package com.minigames.toolkit.dsl.command

import com.minigames.toolkit.extensions.color
import org.bukkit.command.CommandSender

typealias BukkitCommand = org.bukkit.command.Command
typealias CommandScope = CommandInfo.() -> Boolean
typealias Sender = CommandSender

fun command(name: String, commandBlock: BukkitCommand.() -> Unit) =
    Command(name, commandBlock)

class Command(
    name: String,
    commandBlock: BukkitCommand.() -> Unit
) {
    lateinit var scope: CommandScope

    fun execute(block: CommandScope): Command {
        this.scope = block
        return this
    }

    val bukkitCMD = object : BukkitCommand(name) {
        override fun execute(sender: Sender, commandLabel: String, args: Array<String>): Boolean {
            return scope(CommandInfo(sender, args))
        }
    }.apply(commandBlock)

}

data class CommandInfo(
    val sender: Sender,
    val args: Array<String>
) {
    fun reply(message: String) = sender.reply(message)
}

/**
 * Isso daqui é pra quando você quer mandar a mensagem
 * e retornar no comando. Coisa simples que eu uso bastante
 *
 * Ah, e você também pode usar '&' para cores, pois já alterna.
 */
fun Sender.reply(message: String): Boolean {
    sendMessage(message.color)
    return true
}