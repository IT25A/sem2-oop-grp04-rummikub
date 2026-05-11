package hwr.oop.students.group4.rummikub.core

data class Set(
    private val type: SetType,
    private val tiles: List<Tile>

){ init {
    require(tiles.size >= 3) { "At least 3 tiles" }

}
    fun type() = type

}
