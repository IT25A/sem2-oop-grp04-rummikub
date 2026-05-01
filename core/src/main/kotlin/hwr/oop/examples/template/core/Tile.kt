package hwr.oop.examples.template.core

data class Tile(
    private val color: TileColor,
    private val number: TileNumber
) {
    fun color() = color
    fun number() = number
}
