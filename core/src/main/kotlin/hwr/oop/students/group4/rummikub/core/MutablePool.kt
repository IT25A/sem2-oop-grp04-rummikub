package hwr.oop.students.group4.rummikub.core

class MutablePool(
    private val tiles: MutableList<Tile>
) {
    //Commands
    fun draw(count: Int): List<Tile> {
        val rackTiles = mutableListOf<Tile>()
        repeat(count) { rackTiles.add(tiles.removeFirst()) }
        return rackTiles.toList()
    }

    fun toPool() = Pool(tiles)
}
