package me.exerro.eggli.gen

import kotlin.reflect.KProperty

interface GLenumDescriptorContext {
    operator fun String.provideDelegate(thisRef: Any?, property: KProperty<*>): UnitGetterDelegate
}
