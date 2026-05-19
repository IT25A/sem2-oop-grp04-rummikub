package hwr.oop.examples.template.core

data class Set(
    private val tiles: List<Tile>,
) {
    init {
        require(tiles.size >= 3) {"A set must contain at least 3 tiles"}
    }

    val type: SetType by lazy { determineSetType(tiles) }
    val points: Int by lazy { determinePoints(tiles) }
    fun tiles(): List<Tile> = tiles.toList()
    fun type(): SetType = type
    fun points(): Int = points
    private fun determineSetType(tiles: List<Tile>): SetType {
        val numbers = tiles.map { it.number().points() }.distinct().sorted()
        val colors = tiles.map { it.color() }.distinct()

        // GROUP: same number, all different colors (at least 3 colors for valid group)
        if (numbers.size == 1 && colors.size >= 3 && colors.size == tiles.size) {
            return SetType.GROUP
        }

        // RUN: same color, consecutive numbers
        if (colors.size == 1 && numbers.size == tiles.size && isConsecutive(numbers)) {
            return SetType.RUN
        }

        throw IllegalArgumentException("Tiles do not form a valid group or run")
    }

    private fun isConsecutive(numbers: List<Int>): Boolean {
        return numbers.zipWithNext().all { (a, b) -> b == a + 1 }
    }

    private fun determinePoints(tiles: List<Tile>): Int = tiles.sumOf { tile -> tile.number().points() }
}