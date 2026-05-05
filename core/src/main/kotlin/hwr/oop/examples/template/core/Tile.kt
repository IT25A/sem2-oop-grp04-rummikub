package hwr.oop.examples.template.core


data class Tile (
    private val color: TileColor,
    private val number: TileNumber,
    private val points: Int = 0,
) {
    fun color(): TileColor = color
    fun number(): TileNumber = number
    fun points(): Int {
        when (number) {
            TileNumber.ONE -> return 1
            TileNumber.TWO -> return 2
            TileNumber.THREE -> return 3
            TileNumber.FOUR -> return 4
            TileNumber.FIVE -> return 5
            TileNumber.SIX -> return 6
            TileNumber.SEVEN -> return 7
            TileNumber.EIGHT -> return 8
            TileNumber.NINE -> return 9
            TileNumber.TEN -> return 10
            TileNumber.ELEVEN -> return 11
            TileNumber.TWELVE -> return 12
            TileNumber.THIRTEEN -> return 13
            TileNumber.JOKER -> return 0
        }
    }
}