package hwr.oop.examples.template.core

class PoolMutable (
    private val tiles: MutableList<Tile>,
) {
    fun draw(): List<Tile> {
        return listOf(tiles.removeFirst())
    }
    fun toPool(): Pool = Pool(tiles.toList())
}

