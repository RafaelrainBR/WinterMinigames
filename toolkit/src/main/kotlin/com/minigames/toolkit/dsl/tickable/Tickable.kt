package com.minigames.toolkit.dsl.tickable

import com.minigames.toolkit.dsl.module.Module
import com.minigames.toolkit.dsl.module.plugin
import com.minigames.toolkit.dsl.plugin.addTickable
import com.okkero.skedule.schedule

abstract class Tickable(
    val level: TickableLevel = TickableLevel.SECOND
) {
    abstract suspend fun onTick()
}


enum class TickableLevel(val ticks: Int) {

    TICK(1),
    SECOND(20),
    FIVE_SECONDS(20 * 5),
    TEN_SECONDS(20 * 10),
    TWENTY_SECONDS(20 * 20);
}


fun plugin.tick(level: TickableLevel = TickableLevel.SECOND, block: suspend () -> Unit) =
    object : Tickable(level) {
        override suspend fun onTick() = block()
    }.run { addTickable(this) }

fun Module.tick(level: TickableLevel = TickableLevel.SECOND, block: suspend () -> Unit) =
    plugin.tick(level, block)

// TODO botar coroutines e fazer essa joça funcionar

fun plugin.startTick() = schedule {
}