package hwr.oop.examples.template.core

import kotlinx.serialization.Serializable

@Serializable
data class Pool(
    private val tiles: List<Tile>,
) {
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
            return Pool((regularTiles).shuffled())
        }
    }
    //commands
    fun toMutablePool(): PoolMutable = PoolMutable(tiles.toMutableList())
    //queries
    fun tiles(): List<Tile> = tiles
}
