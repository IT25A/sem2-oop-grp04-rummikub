package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

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

    @Test
    fun `all tile numbers exist`() {
        // given
        val tileNumbers = TileNumber.entries
        // when
        // then
        assertThat(tileNumbers).containsExactlyInAnyOrder(
            TileNumber.N1, TileNumber.N2, TileNumber.N3, TileNumber.N4,
            TileNumber.N5, TileNumber.N6, TileNumber.N7, TileNumber.N8,
            TileNumber.N9, TileNumber.N10, TileNumber.N11, TileNumber.N12, TileNumber.N13
        )
    }

    @ParameterizedTest
    @EnumSource(TileNumber::class)
    fun `all tile colors, each tile number can exist`(tileNumber: TileNumber) {
        // given
        val allTileColors = TileColor.entries

        // when
        val tiles = allTileColors.map { Tile(it, tileNumber) }

        // then
        assertThat(tiles)
            .hasSize(allTileColors.size)
            .allMatch { it.number() == tileNumber }

        val colors = tiles.map { it.color() }
        assertThat(colors).containsExactlyInAnyOrderElementsOf(allTileColors)
    }

    @ParameterizedTest
    @EnumSource(TileColor::class)
    fun `all tile numbers, each tile color can exist`(tileColor: TileColor) {
        // given
        val allTileNumbers = TileNumber.entries

        // when
        val tiles = allTileNumbers.map { Tile(tileColor, it) }

        // then
        assertThat(tiles)
            .hasSize(13)
            .allMatch { it.color() == tileColor }

        val numbers = tiles.map { it.number() }
        assertThat(numbers).containsExactlyInAnyOrderElementsOf(allTileNumbers)
    }
}