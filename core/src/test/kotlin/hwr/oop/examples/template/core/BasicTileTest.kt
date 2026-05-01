package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.intArrayOf

class BasicTileTest {
    @Test
    fun `all tile colors exist`() {
        // given
        val tileColors = TileColor.entries
        // when
        // then
        assertThat(tileColors).containsExactlyInAnyOrder(
            TileColor.RED,
            TileColor.BLUE,
            TileColor.BLACK,
            TileColor.YELLOW
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `all tile colors, each number from 1 to 13 can exist`(ints: Int) {
        // given
        val allTileColors = TileColor.entries

        // when
        val tiles = allTileColors.map { Tile(it, ints) }

        // then
        assertThat(tiles)
            .hasSize(allTileColors.size)
            .allMatch { it.number() == ints }

        val colors = tiles.map { it.color() }
        assertThat(colors).containsExactlyInAnyOrderElementsOf(allTileColors)
    }

    @ParameterizedTest
    @ValueSource(ints = [-42, -2, -1, 0, 14, 15, 42])
    fun `all tile colors, invalid numbers cannot exist`(ints: Int) {
        // given
        val allTileColors = TileColor.entries

        // when
        // then
        allTileColors.forEach {
            assertThatThrownBy { Tile(it, ints) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @ParameterizedTest
    @EnumSource(TileColor::class)
    fun `all numbers from 1 to 13, each tile color can exist`(tileColor: TileColor) {
        // given
        val allTileNumbers = 1..13

        // when
        val tiles = allTileNumbers.map { Tile(tileColor, it) }

        // then
        assertThat(tiles)
            .hasSize(13)
            .allMatch { it.color() == tileColor }

        val numbers = tiles.map { it.number() }
        assertThat(numbers).containsExactlyInAnyOrderElementsOf(allTileNumbers)
    }

    @ParameterizedTest
    @EnumSource(TileColor::class)
    fun `invalid numbers, each tile color cannot exist`(tileColor: TileColor) {
        // given
        val invalidTileNumbers = arrayOf(-42, -2, -1, 0, 14, 15, 42)

        // when
        // then
        invalidTileNumbers.forEach {
            assertThatThrownBy { Tile(tileColor, it) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}