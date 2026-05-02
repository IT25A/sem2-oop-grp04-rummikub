package hwr.oop.examples.template.core

class MutablePool (
    private val tiles: MutableList<Tile>,
    ) {
    fun draw(): Tile = tiles.removeFirst()
}
