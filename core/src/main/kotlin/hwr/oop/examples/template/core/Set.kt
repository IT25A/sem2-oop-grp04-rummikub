package hwr.oop.examples.template.core

import kotlinx.serialization.Serializable

@Serializable
data class Set(
    private val tiles: List<Tile>,
) {
    init {
        require(tiles.size >= 3) { "A set must contain at least 3 tiles" }
    }

    // Query
    val type: SetType by lazy { determineSetType(tiles) }
    val points: Int by lazy { determinePoints(tiles) }
    fun tiles(): List<Tile> = tiles
    fun type(): SetType = type
    fun points(): Int = points
    private fun determineSetType(tiles: List<Tile>): SetType {
        //val nonJokerTiles = tiles.filterNot { it.number() == TileNumber.JOKER }
        //val numbers: List<Int> = nonJokerTiles.map { it.number().points() }.distinct().sorted()
        //val colors: List<TileColor> = nonJokerTiles.map { it.color() }.distinct()
        //val jokerCount: Int = tiles.count{ tile -> tile.isJoker() }
        val numbers: List<Int> = tiles.map { it.number().points() }.distinct().sorted()
        val colors: List<TileColor> = tiles.map { it.color() }.distinct()

        // GROUP: same number, all different colors (at least 3 colors for valid group)
        // if(number.size == 1 && colors.size >= 3 - jokerCount && colors.size + jokerCount >= 3) {
        if (numbers.size == 1 && colors.size >= 3 && colors.size == tiles.size) {
            return SetType.GROUP
        }

        // RUN: same color, consecutive numbers
        // if(colors.size == 1 && canFormRun(nonJokerTiles, jokerCount)) {
        if (colors.size == 1 && numbers.size == tiles.size && isConsecutive(numbers)) {
            return SetType.RUN
        }

        throw IllegalArgumentException("Tiles do not form a valid group or run")
    }

    private fun isConsecutive(numbers: List<Int>): Boolean {
        return numbers.zipWithNext().all { (a, b) -> b == a + 1 }
    }
//    private fun canFormRun(nonJokerTiles: List<Tile>, jokerCount: Int): Boolean {
//        val numbers = nonJokerTiles.map { it.number().points() }.sorted()
//        val minNumber = numbers.minOf { it }
//        val maxNumber = numbers.maxOf { it }
//
//        val expectedRange = maxNumber - minNumber + 1
//        val distinctNumbers = numbers.distinct().size
//        val gaps = expectedRange - distinctNumbers
//
//        return jokerCount == gaps
//    }
    private fun determinePoints(tiles: List<Tile>): Int = tiles.sumOf { tile -> tile.number().points() }
}