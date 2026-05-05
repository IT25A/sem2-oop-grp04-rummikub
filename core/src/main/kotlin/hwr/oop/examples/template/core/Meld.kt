package hwr.oop.examples.template.core

data class Meld(
    private val type: MeldType,
    var points: Int = 0,
    private val tiles: List<Tile>,
) {
    init {
        require(tiles.size >= 3) {"A meld must contain at least 3 tiles"}
    }
    fun tiles(): List<Tile> = tiles
    fun type(): MeldType = type
    fun points(): Int = tiles.sumOf { it.points() }
}