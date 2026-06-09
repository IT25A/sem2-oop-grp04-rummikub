package hwr.oop.examples.template.core

import kotlinx.serialization.Serializable

@Serializable
data class Table (
    private val sets: List<Set> = emptyList(),
) {
    fun sets(): List<Set> = sets
    fun isNotEmpty(): Boolean = sets.isNotEmpty()
    fun totalPoints(): Int = sets.sumOf { it.points() }
}