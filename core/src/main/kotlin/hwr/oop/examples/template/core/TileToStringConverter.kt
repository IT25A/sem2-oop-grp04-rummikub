package hwr.oop.examples.template.core

object TileToStringConverter {
    private fun convert(list: List<Tile>): List<String> = list.map { it.asString() }

    fun Tile.asString(): String {
        val sb = StringBuilder()
        sb.append(colors(this.color()))
        sb.append(numbers(this.number()))
        return sb.toString()
    }

    private fun colors(color: TileColor): String {
        when (color) {
            TileColor.BLACK -> return "BK"
            TileColor.BLUE -> return "BL"
            TileColor.RED -> return "RD"
            TileColor.YELLOW -> return "YW"
            TileColor.JOKER -> return "JO"
        }
    }

    private fun numbers(number: TileNumber): String {
        when (number) {
            TileNumber.ONE -> return "01"
            TileNumber.TWO -> return "02"
            TileNumber.THREE -> return "03"
            TileNumber.FOUR -> return "04"
            TileNumber.FIVE -> return "05"
            TileNumber.SIX -> return "06"
            TileNumber.SEVEN -> return "07"
            TileNumber.EIGHT -> return "08"
            TileNumber.NINE -> return "09"
            TileNumber.TEN -> return "10"
            TileNumber.ELEVEN -> return "11"
            TileNumber.TWELVE -> return "12"
            TileNumber.THIRTEEN -> return "13"
            TileNumber.JOKER -> return "KR"
        }
    }
}