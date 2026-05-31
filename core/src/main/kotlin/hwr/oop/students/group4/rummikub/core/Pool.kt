package hwr.oop.students.group4.rummikub.core

data class Pool(
    private val tiles: List<Tile> = (1..2).flatMap {
        TileNumber.entries.flatMap { number ->
            TileColor.entries.map { color ->
                Tile(color, number)
            }
        }
    }.shuffled()
) {

    fun toMutablePool() = MutablePool(tiles.toMutableList())

    //Command


    //Query
    fun tiles() = tiles
}
