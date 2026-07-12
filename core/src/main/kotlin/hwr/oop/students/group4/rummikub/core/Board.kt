package hwr.oop.students.group4.rummikub.core

import kotlinx.serialization.Serializable

@Serializable
data class Board (
    private val sets: List<Set> = emptyList(),
) {
    //Query
    fun sets() = sets
    fun tiles() = sets.flatMap { it.tiles() }
}