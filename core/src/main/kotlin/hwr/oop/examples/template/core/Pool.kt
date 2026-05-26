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
                TileColor.entries.filter { color ->
                    color != TileColor.JOKER }.flatMap { color ->
                        TileNumber.entries.filter { number -> number != TileNumber.JOKER }.map { number ->
                            Tile(color, number)
                    }
                }
            }
            //val jokers: List<Tile> = (1..TILE_COPIES).map { Tile.joker() }
            return Pool((regularTiles).shuffled())
        }
    }
}
