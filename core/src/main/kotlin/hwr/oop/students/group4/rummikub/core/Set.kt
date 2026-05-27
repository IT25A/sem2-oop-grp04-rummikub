package hwr.oop.students.group4.rummikub.core

data class Set(
    private val tiles: List<Tile>
) {
    init {
        require(tiles.size >= 3) { "At least 3 tiles" }
    }

    private val type: SetType by lazy { assignType() }
    //Commands
    private fun assignType(): SetType {
        if (validateGroup(tiles)) return SetType.GROUP
        if (validateRun(tiles)) return SetType.RUN
        throw IllegalArgumentException ("Set is not valid group or run")
    }
    private fun validateGroup(tiles: List<Tile>): Boolean {
        if (tiles.size > 4) return false
        val numbers = tiles.map { it.number() }.distinct()
        if (numbers.size != 1) return false
        val colors = tiles.map { it.color() }.distinct()
        return colors.size == tiles.size
    }
    private fun validateRun(tiles: List<Tile>): Boolean {
        val colors = tiles.map { it.color() }.distinct()
        if (colors.size != 1) return false
        if (tiles.size > 13) return false
        val numbers = tiles.map { it.number() }.distinct()
        if (numbers.size != tiles.size) return false

        val sortedTiles = tiles.map { it.number().value() }.sorted()
        val min = sortedTiles.first()
        val max = sortedTiles.last()

        return (max - min + 1) == tiles.size
    }
    //Queries
    fun tiles() = tiles
    fun type(): SetType = type
}
