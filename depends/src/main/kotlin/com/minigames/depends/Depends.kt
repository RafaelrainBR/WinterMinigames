package com.minigames.depends

import org.bukkit.plugin.java.JavaPlugin

class Depends : JavaPlugin() {

    override fun onEnable() {
        logger.info("Dependencias injetadas com sucesso.")
    }
}