package hwr.oop.examples.template.core

data class Set(
    private val tiles: List<Tile>,
) {
    init {
        require(tiles.size >= 3) {"A set must contain at least 3 tiles"}
    }

    val type: SetType by lazy { determineSetType(tiles) }
    val points: Int by lazy { determinePoints(tiles) }

    fun tiles(): List<Tile> = tiles
    fun type(): SetType = type
    fun points() = points
    fun determineSetType(tiles: List<Tile>): SetType {
        val numbers = tiles.map { it.number().points() }.distinct()
        val colors = tiles.map { it.color() }.distinct()
        val min = numbers.first()
        val max = numbers.last()

        if (numbers.size == 1 && colors.size == tiles.size) return SetType.GROUP
        if (colors.size == 1 && numbers.size == tiles.size && max - min + 1 == tiles.size) return SetType.RUN
        else throw IllegalArgumentException("Tiles do not form a valid group or run")
    }
    fun determinePoints(tiles: List<Tile>): Int = tiles.sumOf { tile -> tile.number().points() }

}