package hwr.oop.examples.template.core

data class Pool(
    private val tiles: List<Tile>,
) {
    fun tiles(): List<Tile> = tiles

    fun toMutablePool(): PoolMutable = PoolMutable(tiles.toMutableList())

    companion object {
        fun createShuffledPool(): Pool {
                val tiles = ((1..2).flatMap {
                    TileColor.entries.flatMap { color ->
                        TileNumber.entries.map { number ->
                            Tile(color, number)
                        }
                    }
                })
                return Pool(tiles.shuffled())
            }
        }
}
