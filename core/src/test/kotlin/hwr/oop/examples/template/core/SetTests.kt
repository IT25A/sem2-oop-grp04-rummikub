package hwr.oop.examples.template.core


import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SetTests {
    //given
    companion object {
        @JvmStatic
        fun faultyTileCombinations(): Stream<List<Tile>> = Stream.of(
            listOf(),
            listOf(
                Tile(TileColor.BLUE, TileNumber.ONE)
            ),
            listOf(
                Tile(TileColor.BLUE, TileNumber.ONE),
                Tile(TileColor.BLUE, TileNumber.TWO)
            )
        )

        @JvmStatic
        fun exceptionTileCombinations(): Stream<List<Tile>> = Stream.of(
            //faulty group with repetition
            listOf(
                Tile(TileColor.BLUE, TileNumber.ONE),
                Tile(TileColor.BLACK, TileNumber.ONE),
                Tile(TileColor.BLUE, TileNumber.ONE)
            ),
            //faulty group too many tiles
            listOf(
                Tile(TileColor.BLUE, TileNumber.ONE),
                Tile(TileColor.BLUE, TileNumber.ONE),
                Tile(TileColor.BLACK, TileNumber.ONE),
                Tile(TileColor.RED, TileNumber.ONE),
                Tile(TileColor.YELLOW, TileNumber.ONE)
            ),
            //faulty run with gap
            listOf(
                Tile(TileColor.BLUE, TileNumber.ONE),
                Tile(TileColor.BLUE, TileNumber.TWO),
                Tile(TileColor.BLUE, TileNumber.THREE),
                Tile(TileColor.BLUE, TileNumber.FOUR),
                Tile(TileColor.BLUE, TileNumber.SIX),
                Tile(TileColor.BLUE, TileNumber.SEVEN)
            ),
            //faulty run with repetition
            listOf(
                Tile(TileColor.BLUE, TileNumber.ONE),
                Tile(TileColor.BLUE, TileNumber.ONE),
                Tile(TileColor.BLUE, TileNumber.TWO),
            )
        )
    }

    @Test
    fun `all set types exist`() {
        // given
        val types = SetType.entries
        // when
        // then
        assertThat(types).containsExactlyInAnyOrder(
            SetType.GROUP,
            SetType.RUN
        )
    }

    @ParameterizedTest
    @MethodSource("faultyTileCombinations")
    fun `proper size not met, exception`(tiles: List<Tile>) {
        //when
        //then
        assertThatThrownBy { Set(tiles) }.hasMessageContaining("must contain at least 3 tiles")
    }

    @ParameterizedTest
    @EnumSource(TileNumber::class)
    fun `all colors, one number makes a group`(number: TileNumber) {
        //given
        val allColors = listOf(
            TileColor.BLACK,
            TileColor.BLUE,
            TileColor.RED,
            TileColor.YELLOW,
        )
        //when
        val tiles = allColors.map { Tile(it, number) }
        //then
        assertThat(Set(tiles).type()).isEqualTo(SetType.GROUP)
        assertThat(Set(tiles).points()).isEqualTo(number.points() * 4)
        assertThat(Set(tiles).tiles()).isEqualTo(tiles)
    }

    @ParameterizedTest
    @EnumSource(TileColor::class)
    fun `all numbers, one color makes a run`(color: TileColor) {
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
        //then
        assertThat(Set(tiles).type()).isEqualTo(SetType.RUN)
        assertThat(Set(tiles).points()).isEqualTo(allNumbers.sumOf { tileNumber -> tileNumber.points() })
        assertThat(Set(tiles).tiles()).isEqualTo(tiles)
    }

    @ParameterizedTest
    @MethodSource("exceptionTileCombinations")
    fun `invalid group or run, exception`(tiles: List<Tile>) {
        //when
        //then
        assertThatThrownBy { Set(tiles).type() }.hasMessageContaining("not form a valid group or run")
    }

}