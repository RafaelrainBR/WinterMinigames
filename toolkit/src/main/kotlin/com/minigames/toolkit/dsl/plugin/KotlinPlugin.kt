package com.minigames.toolkit.dsl.plugin

import com.minigames.toolkit.dsl.command.Command
import com.minigames.toolkit.dsl.module.Module
import com.minigames.toolkit.dsl.module.ModulePriority
import com.minigames.toolkit.dsl.module.plugin
import com.minigames.toolkit.dsl.tickable.Tickable
import com.minigames.toolkit.extensions.accessible
import com.minigames.toolkit.extensions.cast
import com.minigames.toolkit.extensions.color
import com.minigames.toolkit.extensions.getField
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.plugin.java.JavaPlugin

abstract class KotlinPlugin : JavaPlugin() {

    internal val commandMap: CommandMap = server.getField("commandMap").accessible.get(Bukkit.getServer()).cast()
    internal val moduleList = arrayListOf<Module>()

    val tickSet = mutableSetOf<Tickable>()

    override fun onEnable() {
        super.onEnable()
        log("§e§lIniciando plugin...")
        onStart()

        initializeAllModules(ModulePriority.HIGH)
        initializeAllModules(ModulePriority.NORMAL)
        initializeAllModules(ModulePriority.LOWER)
    }

    private fun initializeAllModules(priority: ModulePriority) =
        log("§fInicializando Módulos de prioridade: §e${priority.name}")
            .run {
                moduleList.filter { it.priority == priority }
                    .forEach { module ->
                        log("§fInicializando Módulo: §e§l${module.name}")
                        module.provide()
                        module.commands.forEach { command ->
                            registerCommands(command)
                        }
                    }
            }

    fun log(string: String) = logger.info(string.color)

    abstract fun onStart()
}


fun plugin.registerCommands(vararg commands: Command) =
    commandMap.run {
        registerAll(name,
            commands.map { it.bukkitCMD }.apply {
                forEach { _ ->
                    logger.info("§eRegistrando Comando:§f $name")
                }
            }
        )
    }


fun plugin.registerModules(vararg modules: Module) =
    moduleList.addAll(modules)


fun plugin.addTickable(tickable: Tickable) = tickSet.add(tickable)