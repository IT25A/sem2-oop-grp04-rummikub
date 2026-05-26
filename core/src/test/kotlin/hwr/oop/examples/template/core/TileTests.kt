package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class TileTests {

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
            TileColor.JOKER,
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
        assertFalse(tiles.all { tile -> tile.isJoker() })
    }
    //TO DO: Add JOKER
    @ParameterizedTest
    @EnumSource(TileColor::class)
    fun `all numbers, points do exist`(color: TileColor) {
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
        val tiles: List<Tile> = allNumbers.map { Tile(color, it) }
        val points: List<Int> = tiles.map { it.number().points() }
        val sum = points.sum()
        //then
        assertThat(sum).isEqualTo(91)
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
        val tiles = allColors.map { Tile(it, number) }
        val colors = tiles.map { it.color() }

        //then
        assertThat(tiles).hasSize(allColors.size).allMatch{ it.number() == number }
        assertThat(colors).containsExactlyInAnyOrderElementsOf(allColors)
        assertFalse(tiles.all { tile -> tile.isJoker() })
    }

    @Test
    fun `jokerTiles exist`() {
        //given
        val tile = Tile.joker()
        val tile2 = Tile(TileColor.BLUE, TileNumber.SEVEN)
        //when
        //then
        assertThat(tile.color()).isEqualTo(TileColor.JOKER)
        assertThat(tile.number()).isEqualTo(TileNumber.JOKER)
        assertTrue(tile.isJoker())
        assertFalse(tile2.isJoker())
    }
}