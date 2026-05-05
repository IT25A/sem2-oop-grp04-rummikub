package hwr.oop.examples.template.core

class RackMutable (
    private val tiles: MutableList<Tile>,
) {
    fun addTile(tile: Tile) {
        tiles.add(tile)
    }

}