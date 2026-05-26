package hwr.oop.examples.template.core

object TileToStringConverter {
    fun convert(vararg tile: Tile) = convert(tile.toList())

    private fun convert(tileList: List<Tile>): List<String> = tileList.map{ it.asString() }

    fun Tile.asString(): String {
        val sb = StringBuilder()
        sb.append(colors(this.color()))
        sb.append(numbers(this.number()))
        return sb.toString()
    }

    private fun colors(color: TileColor): String {
        return when (color) {
            TileColor.BLACK -> "BK"
            TileColor.BLUE -> "BL"
            TileColor.RED -> "RD"
            TileColor.YELLOW -> "YW"
            TileColor.JOKER -> "JO"
        }
    }

    private fun numbers(number: TileNumber): String {
        return when (number) {
            TileNumber.ONE -> "01"
            TileNumber.TWO -> "02"
            TileNumber.THREE -> "03"
            TileNumber.FOUR -> "04"
            TileNumber.FIVE -> "05"
            TileNumber.SIX -> "06"
            TileNumber.SEVEN -> "07"
            TileNumber.EIGHT -> "08"
            TileNumber.NINE -> "09"
            TileNumber.TEN -> "10"
            TileNumber.ELEVEN -> "11"
            TileNumber.TWELVE -> "12"
            TileNumber.THIRTEEN -> "13"
            TileNumber.JOKER -> "KR"
        }
    }
}