package hwr.oop.examples.template.core

class PoolMutable (
    private val tiles: MutableList<Tile>,
    ) {
    fun draw(): List<Tile> {
        if (tiles.isEmpty()) throw Exception("Draw pool is empty")
        return listOf(tiles.removeFirst())
    }
}
