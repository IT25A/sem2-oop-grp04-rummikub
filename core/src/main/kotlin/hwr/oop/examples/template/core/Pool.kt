package hwr.oop.examples.template.core

data class Pool(
    private val tiles: List<Tile>,
){
    fun tiles(): List<Tile> = tiles

    fun toMutablePool(): MutablePool = MutablePool(tiles.toMutableList())

    companion object {
        fun createShuffledPool(): Pool = Pool(
            ((1..2).flatMap {
                TileColor.entries.filter{ it != TileColor.JOKER }.flatMap { color ->
                    TileNumber.entries.filter{ it != TileNumber.JOKER }.map { number ->
                        Tile(color, number)
                    }
                }
            } + listOf(
                Tile(TileColor.JOKER, TileNumber.JOKER),
                Tile(TileColor.JOKER, TileNumber.JOKER),
            )).shuffled()
        )
    }
}