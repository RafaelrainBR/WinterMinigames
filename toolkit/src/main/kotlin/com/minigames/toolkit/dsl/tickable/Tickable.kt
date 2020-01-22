package com.minigames.toolkit.dsl.tickable

abstract class Tickable(val level: TickableLevel = TickableLevel.SECOND) {

    abstract fun onTick()
}

enum class TickableLevel(val ticks: Int) {

    TICK(1),
    SECOND(20),
    FIVE_SECONDS(20 * 5),
    TEN_SECONDS(20 * 10),
    TWENTY_SECONDS(20 * 20);
}