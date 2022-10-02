package me.exerro.eggli.gen

import kotlin.reflect.KProperty

object UnitGetterDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = Unit
}
