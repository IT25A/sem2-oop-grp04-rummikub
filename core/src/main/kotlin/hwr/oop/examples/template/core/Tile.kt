package hwr.oop.examples.template.core


data class Tile (
    private val color: TileColor,
    private val number: TileNumber,
    private val points: Int = 0,
) {
    fun color(): TileColor = color
    fun number(): TileNumber = number
    fun points(): Int {
        return when (number) {
            TileNumber.ONE -> 1
            TileNumber.TWO -> 2
            TileNumber.THREE -> 3
            TileNumber.FOUR -> 4
            TileNumber.FIVE -> 5
            TileNumber.SIX -> 6
            TileNumber.SEVEN -> 7
            TileNumber.EIGHT -> 8
            TileNumber.NINE -> 9
            TileNumber.TEN -> 10
            TileNumber.ELEVEN -> 11
            TileNumber.TWELVE -> 12
            TileNumber.THIRTEEN -> 13
            TileNumber.JOKER -> 0
        }
    }
}