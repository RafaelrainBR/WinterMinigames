@file:Suppress("UNCHECKED_CAST")

package com.minigames.toolkit.extensions

import java.lang.reflect.Field

fun Any.getField(name: String): Field = javaClass.getDeclaredField(name)

val Field.accessible get() = apply { isAccessible = true }

fun <T : Any> Any.cast() = this as T