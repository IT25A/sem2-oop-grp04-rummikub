package hwr.oop.students.group4.rummikub.core

data class Pool(
    private val tiles: List<Tile>,
) {
    //Command
    companion object {
        fun createShuffledPool(): Pool {
            val tiles = (1..2).flatMap {
                TileNumber.entries.flatMap { number ->
                    TileColor.entries.map { color ->
                        Tile(color, number)
                    }
                }
            }.shuffled()

            return Pool(tiles)
        }
    }

    fun toMutablePool() = MutablePool(tiles.toMutableList())

    //Query
    fun tiles() = tiles
}
