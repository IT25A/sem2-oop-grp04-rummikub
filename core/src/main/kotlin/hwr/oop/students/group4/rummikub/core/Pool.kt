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
    //Command
    fun draw(count: Int): Pair<Pool, List<Tile>> {
        val rackTiles = tiles.subList(0, count)
        val newPoolTiles = tiles.toMutableList().apply { rackTiles.forEach { remove(it) }
        }.toList()
        return Pair(copy(tiles = newPoolTiles), rackTiles)
    }

    //Query
    fun tiles() = tiles
}
