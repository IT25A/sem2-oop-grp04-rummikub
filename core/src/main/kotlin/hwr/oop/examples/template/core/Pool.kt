package hwr.oop.examples.template.core

data class Pool (
    private val tiles: List<Tile>,
) {
    fun tiles(): List<Tile> = tiles

    fun toMutableList(): MutablePool = MutablePool(tiles.toMutableList())
}