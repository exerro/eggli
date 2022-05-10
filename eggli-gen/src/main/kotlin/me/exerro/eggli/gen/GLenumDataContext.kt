package me.exerro.eggli.gen

import kotlin.reflect.KProperty

interface GLenumDataContext {
    val noDocumentation: String
    fun options(block: context (GLenumDescriptorContext) () -> String): GLenumDescriptor
    operator fun GLenumDescriptor.provideDelegate(thisRef: Any?, property: KProperty<*>): UnitGetterDelegate
}

