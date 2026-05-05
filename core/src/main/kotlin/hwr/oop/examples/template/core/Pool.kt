package hwr.oop.examples.template.core

data class Pool(
    private val tiles: List<Tile>,
){
    fun tiles(): List<Tile> = tiles

    fun toMutablePool(): PoolMutable = PoolMutable(tiles.toMutableList())

    companion object {
        fun createShuffledPool(includeJoker: Boolean): Pool {
            if (includeJoker) {
                val tiles = ((1..2).flatMap {
                    TileColor.entries.filter{ it != TileColor.JOKER }.flatMap { color ->
                        TileNumber.entries.filter{ it != TileNumber.JOKER }.map { number ->
                            Tile(color, number)
                        }
                    }
                }) + listOf<Tile>(
                    Tile(TileColor.JOKER,TileNumber.JOKER),
                    Tile(TileColor.JOKER, TileNumber.JOKER))
                return Pool(tiles.shuffled())
            }
            else {
                val tiles = ((1..2).flatMap {
                    TileColor.entries.filter{ it != TileColor.JOKER }.flatMap { color ->
                        TileNumber.entries.filter{ it != TileNumber.JOKER }.map { number ->
                            Tile(color, number)
                        }
                    }
                })
                return Pool(tiles.shuffled())
            }
        }
    }
}