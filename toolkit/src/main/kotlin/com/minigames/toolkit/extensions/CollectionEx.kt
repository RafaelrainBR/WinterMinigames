package com.minigames.toolkit.extensions

inline fun <T> Collection<T>.all(block: T.() -> Unit) = forEach(block)