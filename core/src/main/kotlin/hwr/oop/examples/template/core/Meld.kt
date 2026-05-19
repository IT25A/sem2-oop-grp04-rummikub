package hwr.oop.examples.template.core

data class Meld (
    private val sets: MutableList<Set>,
    private val owner: PlayerId,
) {
    fun sets(): List<Set> = sets.toList()

    fun owner(): PlayerId = owner
    fun points(): Int = sets.sumOf { it.points() }

    fun setCount(): Int = sets.size

    fun allTiles(): List<Tile> = sets.flatMap { it.tiles() }

    companion object {
        fun fromSet(set: Set, owner: PlayerId): Meld {
            return Meld(mutableListOf(set), owner)
        }

        fun fromSets(owner: PlayerId, vararg sets: Set): Meld {
            return Meld(sets.toMutableList(), owner)
        }
    }
}