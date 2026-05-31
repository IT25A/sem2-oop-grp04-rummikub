package hwr.oop.students.group4.rummikub.core

class MutablePool(
    private val tiles: MutableList<Tile>
) {
    fun draw(count: Int): List<Tile> {
        val rackTiles = tiles.take(count)
        (1..count).forEach { _ -> tiles }
        return rackTiles
    }

    fun toPool() = Pool(tiles)
}
