package hwr.oop.examples.template.core

import hwr.oop.examples.template.core.StringToTileConverter.asTile
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TileToStringTests {
    private val converter = TileToStringConverter

    @ParameterizedTest
    @CsvSource(
        "BK01, BLACK, ONE",
        "BK02, BLACK, TWO",
        "BK03, BLACK, THREE",
        "BK04, BLACK, FOUR",
        "BL05, BLUE, FIVE",
        "BL06, BLUE, SIX",
        "BL07, BLUE, SEVEN",
        "BL08, BLUE, EIGHT",
        "RD09, RED, NINE",
        "RD10, RED, TEN",
        "RD11, RED, ELEVEN",
        "RD12, RED, TWELVE",
        "YW13, YELLOW, THIRTEEN",
        "YW01, YELLOW, ONE",
        "YW02, YELLOW, TWO",
        "YW03, YELLOW, THREE",
        "JOKR, JOKER, JOKER"
    )
    fun `tile correctly parsed to string, asString`(output: String, color: TileColor, number: TileNumber) {
        //when
        val str = with(converter) {
            val tile = Tile(color, number)
            tile.asString()
        }
        //then
        assertThat(str).isEqualTo(output)
    }

    @ParameterizedTest
    @CsvSource(
        "BK01, BLACK, ONE",
        "BK02, BLACK, TWO",
        "BK03, BLACK, THREE",
        "BK04, BLACK, FOUR",
        "BL05, BLUE, FIVE",
        "BL06, BLUE, SIX",
        "BL07, BLUE, SEVEN",
        "BL08, BLUE, EIGHT",
        "RD09, RED, NINE",
        "RD10, RED, TEN",
        "RD11, RED, ELEVEN",
        "RD12, RED, TWELVE",
        "YW13, YELLOW, THIRTEEN",
        "YW01, YELLOW, ONE",
        "YW02, YELLOW, TWO",
        "YW03, YELLOW, THREE",
        "JOKR, JOKER, JOKER"
    )
    fun `tile correctly parsed to string, convert`(output: String, color: TileColor, number: TileNumber) {
        //when
        val str = with(converter) {
            val tile = Tile(color, number)
            convert(tile)
        }
        //then
        assertThat(str).isEqualTo(listOf(output))
    }
}