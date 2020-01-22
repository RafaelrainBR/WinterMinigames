package com.minigames.toolkit.dsl.plugin

import com.minigames.toolkit.dsl.command.Command
import com.minigames.toolkit.dsl.module.Module
import com.minigames.toolkit.dsl.module.ModulePriority
import com.minigames.toolkit.extensions.accessible
import com.minigames.toolkit.extensions.cast
import com.minigames.toolkit.extensions.color
import com.minigames.toolkit.extensions.getField
import org.bukkit.command.CommandMap
import org.bukkit.plugin.java.JavaPlugin

abstract class KotlinPlugin : JavaPlugin() {

    private val commandMap: CommandMap = server.getField("commandMap").accessible.cast()
    private val moduleList = arrayListOf<Module>()

    override fun onEnable() {
        super.onEnable()
        log("§e§lIniciando plugin...")
        onStart()

        //INICIALIZANDO MODULOS OH YEAH
        initializeAllModules(ModulePriority.HIGH)
        initializeAllModules(ModulePriority.NORMAL)
        initializeAllModules(ModulePriority.LOWER)
    }

    fun registerCommands(vararg commands: Command) {
        commandMap.run {
            registerAll(name,
                commands.map { it.bukkitCMD }.apply {
                    forEach { _ ->
                        logger.info("§eRegistrando Comando:§f $name")
                    }
                }
            )
        }
    }

    fun registerModules(vararg modules: Module) {
        moduleList.addAll(modules)
    }

    private fun initializeAllModules(priority: ModulePriority) {
        log("§fInicializando Módulos de prioridade: §e${priority.name}")
        moduleList.filter { it.priority == priority }
            .forEach {
                log("§fInicializando Módulo: §e§l${it.name}")
                it.provide()
            }
    }

    fun log(string: String) = logger.info(string.color)

    abstract fun onStart()
}