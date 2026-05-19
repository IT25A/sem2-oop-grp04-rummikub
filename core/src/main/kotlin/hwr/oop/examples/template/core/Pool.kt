package hwr.oop.examples.template.core

data class Pool(
    private val tiles: List<Tile>,
) {
    fun tiles(): List<Tile> = tiles

    fun toMutablePool(): PoolMutable = PoolMutable(tiles.toMutableList())

    companion object {
        private const val TILE_COPIES = 2
        fun createShuffledPool(): Pool {
            val regularTiles = (1..TILE_COPIES).flatMap {
                TileColor.entries.flatMap { color ->
                    TileNumber.entries.map { number ->
                        Tile(color, number)
                    }
                }
            }
            return Pool((regularTiles).shuffled())
        }
    }
}
