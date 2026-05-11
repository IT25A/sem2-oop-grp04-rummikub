package hwr.oop.examples.template.core

class PoolMutable (
    private val tiles: MutableList<Tile>,
    ) {
    fun tiles(): MutableList<Tile> = tiles
    fun draw(): List<Tile> {
        if (tiles.isEmpty()) throw Exception("Pool is empty")
        return listOf(tiles.removeFirst())
    }
}
