package hwr.oop.examples.template.core

data class Tile (
    private val color: TileColor,
    private val number: TileNumber,
) {
    fun color(): TileColor = color
    fun number(): TileNumber = number
    fun isJoker(): Boolean = number == TileNumber.JOKER && color == TileColor.JOKER

    companion object {
        fun joker(): Tile = Tile(TileColor.JOKER, TileNumber.JOKER)
    }


}