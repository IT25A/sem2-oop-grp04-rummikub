package hwr.oop.examples.template.core

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class PlayerId (private val id: String) {
    fun id() = id
}