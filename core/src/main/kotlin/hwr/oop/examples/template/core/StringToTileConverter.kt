package hwr.oop.examples.template.core

object StringToTileConverter {
    fun convert(vararg strings: String) = convert(strings.toList())

    private fun convert(list : List<String>):List<Tile> = list.map{ it.asTile() }

    fun String.asTile(): Tile {
        require(isNotEmpty()) { "Tile must not be empty" }
        require(isNotBlank()) { "Tile must not be blank" }
        require(this.length == 4) {"Tile string must be exactly 4 characters long"}
        val uppercase: String = this.uppercase()
        return when (uppercase) {
            "JOKR" -> Tile(TileColor.JOKER, TileNumber.JOKER)
            else -> {
                val colorChar = uppercase.take(2)
                val numberChar = uppercase.takeLast(2)
                Tile(colors(colorChar), numbers(numberChar))
            }
        }
        }

    private fun colors(sub: String): TileColor = when (sub) {
        "BK" -> TileColor.BLACK
        "BL" -> TileColor.BLUE
        "RD" -> TileColor.RED
        "YW" -> TileColor.YELLOW
        else -> throw IllegalArgumentException("$sub is not a valid color")
    }

    private fun numbers(sub: String): TileNumber = when (sub) {
        "01" -> TileNumber.ONE
        "02" -> TileNumber.TWO
        "03" -> TileNumber.THREE
        "04" -> TileNumber.FOUR
        "05" -> TileNumber.FIVE
        "06" -> TileNumber.SIX
        "07" -> TileNumber.SEVEN
        "08" -> TileNumber.EIGHT
        "09" -> TileNumber.NINE
        "10" -> TileNumber.TEN
        "11" -> TileNumber.ELEVEN
        "12" -> TileNumber.TWELVE
        "13" -> TileNumber.THIRTEEN
        else -> throw IllegalArgumentException("$sub is not a valid number")
    }
    }
