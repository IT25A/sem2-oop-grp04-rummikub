package hwr.oop.examples.template.core

data class Set(
    private val type: SetType,
    var points: Int = 0,
    private val tiles: List<Tile>,
) {
    init {
        require(tiles.size >= 3) {"A meld must contain at least 3 tiles"}
    }
    fun tiles(): List<Tile> = tiles
    fun type(): SetType = type
    fun points(): Int = tiles.sumOf { it.points() }
}