package hwr.oop.examples.template.core

class BoardMutable (
    private val sets: MutableList<Set>
) {
    fun sets(): List<Set> = sets.toList()

    fun toBoard(): Board = Board(sets)

    fun addSet(set: Set): BoardMutable {
        sets.add(set)
        return this
    }
    fun addMeld(meld: Meld): BoardMutable {
        sets.addAll(meld.sets())
        return this
    }
    fun removeSet(set: Set) {
        require(sets.contains(set)) { "Cannot remove non-existing set: $set" }
        sets.remove(set)
    }
    fun allTiles(): List<Tile> = sets.flatMap { it.tiles() }
    fun isEmpty(): Boolean = sets.isEmpty()
    fun totalPoints(): Int = sets.sumOf { it.points() }
}