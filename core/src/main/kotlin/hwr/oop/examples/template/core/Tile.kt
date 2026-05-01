package hwr.oop.examples.template.core

import org.jetbrains.annotations.Range

data class Tile(
    private val color: TileColor,
    private val number: @Range(from = 1, to = 13) Int
) {
    init {
        require(number in 1..13) { "number must be between 1 and 13 (was $number)" }
    }
    fun color() = color
    fun number() = number
}
