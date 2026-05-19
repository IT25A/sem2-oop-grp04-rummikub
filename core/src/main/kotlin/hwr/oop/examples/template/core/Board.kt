package hwr.oop.examples.template.core

data class Board (
    private val sets: List<Set> = emptyList(),
) {
    fun sets(): List<Set> = sets

    fun toMutableBoard(): BoardMutable = BoardMutable(sets.toMutableList())

    /** Returns true if the board is empty. */
    fun isEmpty(): Boolean = sets.isEmpty()

    /** Returns the total points of all sets on the board. */
    fun totalPoints(): Int = sets.sumOf { it.points() }

    /** Creates a Board from a list of sets. */
    companion object {
        fun fromSets(sets: List<Set>): Board = Board(sets)
    }
}