package hwr.oop.students.group4.rummikub.core

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class PlayerId(
    private val playerId: String

) {
    fun playerId(): String = playerId
}