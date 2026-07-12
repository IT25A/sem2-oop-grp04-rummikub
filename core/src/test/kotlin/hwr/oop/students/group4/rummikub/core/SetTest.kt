package hwr.oop.students.group4.rummikub.core
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class SetTest {

    companion object {
        @JvmStatic
        fun tileCombinations(): Stream<List<Tile>> = Stream.of(
            listOf(),
            listOf(
                Tile(TileColor.BLUE, TileNumber.FOUR)
            ),
            listOf(
                Tile(TileColor.BLUE, TileNumber.FOUR),
                Tile(TileColor.RED, TileNumber.FOUR)
            )
        )
    }

    @Test
    fun `valid SetTypes exist`() {
        //given
        val setTypes = SetType.entries
        //when
        //then
        assertThat(setTypes).containsExactlyInAnyOrder(
            SetType.RUN,
            SetType.GROUP
        )
    }

    @ParameterizedTest
    @MethodSource("tileCombinations")
    fun `set has less than 3 Tiles, exception`(tiles: List<Tile>) {
        assertThatThrownBy { Set(tiles).type() }.hasMessageContaining("At least 3 tiles")
    }

    @Test
    fun `list of tiles is a valid group`() {
        //given
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.RED, TileNumber.FOUR),
            Tile(TileColor.YELLOW, TileNumber.FOUR)
        )
        //when
        val type = Set(tiles).type()
        //then
        assertThat(type).isEqualTo(SetType.GROUP)
    }

    @Test
    fun `list of tiles is not group, bigger than four`() {
        //given
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.RED, TileNumber.FOUR),
            Tile(TileColor.YELLOW, TileNumber.FOUR),
            Tile(TileColor.BLACK, TileNumber.FOUR),
            Tile(TileColor.RED, TileNumber.FOUR)
        )
        //when
        //then
        assertThatThrownBy { Set(tiles).type() }.hasMessageContaining("not valid group or run")
    }

    @Test
    fun `list of tiles is not group, not same number`() {
        //given
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.RED, TileNumber.THREE),
            Tile(TileColor.YELLOW, TileNumber.FOUR)
        )
        //when
        //then
        assertThatThrownBy { Set(tiles).type() }.hasMessageContaining("not valid group or run")
    }

    @Test
    fun `list of tiles is not group, not same color`() {
        //given
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.YELLOW, TileNumber.FOUR)
        )
        //when
        //then
        assertThatThrownBy { Set(tiles).type() }.hasMessageContaining("not valid group or run")
    }

    @Test
    fun `list of tiles is valid run`() {
        //given
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.BLUE, TileNumber.FIVE),
            Tile(TileColor.BLUE, TileNumber.SIX)
        )
        //when
        val type = Set(tiles).type()
        //then
        assertThat(type).isEqualTo(SetType.RUN)
    }

    @Test
    fun `list of tiles is not a valid run, different colors`() {
        //given
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.RED, TileNumber.FIVE),
            Tile(TileColor.YELLOW, TileNumber.SIX)
        )
        //when
        //then
        assertThatThrownBy { Set(tiles).type() }.hasMessageContaining("not valid group or run")
    }

    @Test
    fun `list of tiles is not a valid run, too many tiles`() {
        //given
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.ONE),
            Tile(TileColor.BLUE, TileNumber.TWO),
            Tile(TileColor.BLUE, TileNumber.THREE),
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.BLUE, TileNumber.FIVE),
            Tile(TileColor.BLUE, TileNumber.SIX),
            Tile(TileColor.BLUE, TileNumber.SEVEN),
            Tile(TileColor.BLUE, TileNumber.SEVEN),
            Tile(TileColor.BLUE, TileNumber.EIGHT),
            Tile(TileColor.BLUE, TileNumber.NINE),
            Tile(TileColor.BLUE, TileNumber.TEN),
            Tile(TileColor.BLUE, TileNumber.ELEVEN),
            Tile(TileColor.BLUE, TileNumber.TWELVE),
            Tile(TileColor.BLUE, TileNumber.THIRTEEN),
            Tile(TileColor.BLUE, TileNumber.ONE),
        )
        //when
        //then
        assertTrue(Set(tiles).tiles().size == tiles.size)
        assertFalse(Set(tiles).tiles().size == 13)
        assertThatThrownBy { Set(tiles).type() }.hasMessageContaining("not valid group or run")
    }

    @Test
    fun `list of tiles is not a valid run, repeated number`() {
        //given
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.ONE),
            Tile(TileColor.BLUE, TileNumber.TWO),
            Tile(TileColor.BLUE, TileNumber.THREE),
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.BLUE, TileNumber.ONE),
        )
        //when
        //then
        assertThatThrownBy { Set(tiles).type() }.hasMessageContaining("not valid group or run")
    }

    @Test
    fun `list of tiles is not a valid run, gaps found in tiles`() {
        //given
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.ONE),
            Tile(TileColor.BLUE, TileNumber.TWO),
            Tile(TileColor.BLUE, TileNumber.FIVE),
            Tile(TileColor.BLUE, TileNumber.SEVEN),
            Tile(TileColor.BLUE, TileNumber.EIGHT),
        )
        //when
        //then
        assertThatThrownBy { Set(tiles).type() }.hasMessageContaining("not valid group or run")
    }


}
