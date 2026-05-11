package hwr.oop.examples.template.core

data class Set(
    private val tiles: List<Tile>,
    var points: Int = 0,
) {
    init {
        require(tiles.size >= 3) {"A meld must contain at least 3 tiles"}
    }

    val type: SetType by lazy { determineSetType() }

    fun tiles(): List<Tile> = tiles
    fun type(): SetType = type
    fun points(): Int = tiles.sumOf { it.number().points() }
    fun determineSetType(): SetType {
        if (isGroup(tiles)) return SetType.GROUP
        if (isRun(tiles)) return SetType.RUN
        else throw IllegalArgumentException("Tiles do not form a valid group or run")
    }
    fun isGroup(tiles: List<Tile>): Boolean {
        if (tiles.size !in 3..4) return false

        val jokerCount = tiles.count { it.number() == TileNumber.JOKER && it.color() == TileColor.JOKER }
        require (jokerCount <= 1) { "Joker can be maximum 1 in a Group" }

        val numTiles = tiles.filter { it.number() != TileNumber.JOKER && it.color() != TileColor.JOKER }

        val numbers = numTiles.map { it.number() }.distinct()
        val colors = numTiles.map { it.color() }.distinct()

        return numbers.size == 1 && colors.size == tiles.size
    }
    fun isRun(tiles: List<Tile>): Boolean {
        val jokerCount = tiles.count { it.number() == TileNumber.JOKER && it.color() == TileColor.JOKER }
        val numTiles = tiles.filter { it.number() != TileNumber.JOKER && it.color() != TileColor.JOKER }
        val colors = numTiles.map { it.color() }.distinct()

        if (colors.size != 1) return false

        val numbers = numTiles.map { it.number().points() }.sorted()
        val min = numbers.first()
        val max = numbers.last()

        if (max - min + 1 > tiles.size) return false
        if (max > 13 || min < 1) return false

        val gaps = (min..max).count { it !in numbers } + (tiles.size - (max - min + 1))
        return gaps <= jokerCount
    }

}