package hwr.oop.examples.template.core

data class Board (
    private val sets: List<Set> = emptyList(),
) {
    fun sets(): List<Set> = sets

    fun toMutableBoard() = Board(sets.toMutableList())
}