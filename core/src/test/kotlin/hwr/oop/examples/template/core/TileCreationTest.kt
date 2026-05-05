package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class TileCreationTest {
    // delete every one but the last. because that one coveres everything
    @Test
    fun `all tile colors exist`() {
        // given
        val colors = TileColor.entries
        // when
        // then
        assertThat(colors).containsExactlyInAnyOrder(
            TileColor.BLACK,
            TileColor.BLUE,
            TileColor.RED,
            TileColor.YELLOW,
            TileColor.JOKER
        )
    }
    @ParameterizedTest
    @EnumSource(TileColor::class)
    fun `all numbers, color does exist`(color: TileColor) {
        //given
        val allNumbers = listOf(
            TileNumber.ONE,
            TileNumber.TWO,
            TileNumber.THREE,
            TileNumber.FOUR,
            TileNumber.FIVE,
            TileNumber.SIX,
            TileNumber.SEVEN,
            TileNumber.EIGHT,
            TileNumber.NINE,
            TileNumber.TEN,
            TileNumber.ELEVEN,
            TileNumber.TWELVE,
            TileNumber.THIRTEEN,
        )
        //when
        val tiles = allNumbers.map { Tile(color, it) }
        val numbers = tiles.map { it.number() }

        //then
        assertThat(tiles).hasSize(allNumbers.size)
            .allMatch { it.color() == color }

        assertThat(numbers).containsExactlyInAnyOrderElementsOf(allNumbers)
    }

    @Test
    fun `all tile numbers exist`() {
        // given
        val numbers = TileNumber.entries
        // when
        // then
        assertThat(numbers).containsExactlyInAnyOrder(
            TileNumber.ONE,
            TileNumber.TWO,
            TileNumber.THREE,
            TileNumber.FOUR,
            TileNumber.FIVE,
            TileNumber.SIX,
            TileNumber.SEVEN,
            TileNumber.EIGHT,
            TileNumber.NINE,
            TileNumber.TEN,
            TileNumber.ELEVEN,
            TileNumber.TWELVE,
            TileNumber.THIRTEEN,
            TileNumber.JOKER
        )
    }

    @ParameterizedTest
    @EnumSource(TileNumber::class)
    fun `all colors, number does exist`(number: TileNumber) {
        //given
        val allColors = listOf(
            TileColor.BLACK,
            TileColor.BLUE,
            TileColor.RED,
            TileColor.YELLOW,
        )

        //when
        val tiles = allColors.map { Tile(it, number) } // this creates 4 Tiles, one of each color, for the current number
        val colors = tiles.map { it.color() } // this creates a list of all colors used in `val tiles`. If this is the same as allColors, test succeeds

        //then
        assertThat(tiles).hasSize(allColors.size).allMatch{ it.number() == number }
        assertThat(colors).containsExactlyInAnyOrderElementsOf(allColors)
    }
}