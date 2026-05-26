package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class StringToTileTests {
    private val converter = StringToTileConverter

    @ParameterizedTest
    @CsvSource(
    "BK01, BLACK, ONE",
        "bk02, BLACK, TWO",
        "Bk03, BLACK, THREE",
        "bK04, BLACK, FOUR",
        "BL05, BLUE, FIVE",
        "bl06, BLUE, SIX",
        "Bl07, BLUE, SEVEN",
        "bL08, BLUE, EIGHT",
        "RD09, RED, NINE",
        "rd10, RED, TEN",
        "Rd11, RED, ELEVEN",
        "rD12, RED, TWELVE",
        "YW13, YELLOW, THIRTEEN",
        "yw01, YELLOW, ONE",
        "yW02, YELLOW, TWO",
        "Yw03, YELLOW, THREE",
    )
    fun `string correctly parsed to Tile`(input: String, color: TileColor, number: TileNumber) {
        //when
        val tile = with(converter) {
            input.asTile()
        }
        //then
        assertThat(tile.number()).isEqualTo(number)
        assertThat(tile.color()).isEqualTo(color)
    }

    @ParameterizedTest
    @CsvSource(
        "bc01",
        "bu04",
        "re10",
        "yl13"
    )
    fun `false colors substring, exception`(input: String) {
        //when
        //then
        assertThatThrownBy {converter.convert(input)}.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {converter.convert(input)}.hasMessageContaining("is not a valid color")
    }

    @ParameterizedTest
    @CsvSource(
        "Bk00",
        "bl14",
        "rd67",
        "ywT0"
    )
    fun `false numbers substring, exception`(input: String) {
        //when
        //then
        assertThatThrownBy {converter.convert(input)}.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {converter.convert(input)}.hasMessageContaining("is not a valid number")
    }

    @ParameterizedTest
    @CsvSource(
        "B",
        "BI",
        "0",
        "01",
        "B01",
        "BLUE04",
        "RD0001",
        "Yellow13"
    )
    fun `string not proper length, exception`(input: String) {
        //when
        //then
        assertThatThrownBy {converter.convert(input)}.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {converter.convert(input)}.hasMessageContaining("must be exactly 4 characters long")
    }

    @Test
    fun `string blank, exception thrown`() {
        //when
        val tileBlank = " "
        //then
        assertThatThrownBy {converter.convert(tileBlank)}.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {converter.convert(tileBlank)}.hasMessageContaining("Tile must not be blank")
    }
    @Test
    fun `string empty, exception`() {
        //when
        val tileString = ""
        //then
        assertThatThrownBy {converter.convert(tileString)}.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {converter.convert(tileString)}.hasMessageContaining("Tile must not be empty")
    }
}