package hwr.oop.students.group4.rummikub.core

data class Pool(
    private val tiles: MutableList<Tile> = (1..2).flatMap {
        TileNumber.entries.flatMap { number ->
            TileColor.entries.map { color ->
                Tile(color, number)
            }
        }
    }.shuffled().toMutableList()
) {
    fun tiles() = tiles
    fun draw(): Tile = tiles.removeFirst()
}
