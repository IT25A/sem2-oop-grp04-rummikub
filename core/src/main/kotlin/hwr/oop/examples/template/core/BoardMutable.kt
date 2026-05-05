package hwr.oop.examples.template.core

class BoardMutable (
    private val sets: MutableList<Set>
) {
    fun sets(): MutableList<Set> = sets
    fun addSet(set: Set) {
        sets.add(set)
    }
    fun removeSet(set: Set) {
        require(sets.remove(set)) { "Cannot remove non-existing set: $set" }
    }
    fun allTiles(): List<Tile> = sets.flatMap { it.tiles() }
    fun isEmpty(): Boolean = sets.isEmpty()
    fun totalPoints(): Int = sets.sumOf { it.points() }
}