package hwr.oop.examples.template.core

data class Rack(
    private val owner: PlayerId,
    private val tiles: List<Tile>,
    private val opened: Boolean = false,
) {
    fun owner() : PlayerId = owner
    fun tiles(): List<Tile> = tiles
    private fun isOpened(): Boolean = true
    fun isOpen(): Boolean = opened
}