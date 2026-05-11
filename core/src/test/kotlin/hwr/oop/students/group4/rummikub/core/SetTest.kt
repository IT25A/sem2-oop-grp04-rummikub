package hwr.oop.students.group4.rummikub.core
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SetTest {
    @Test
    fun validSetTypesExist() {
        //given
        val setTypes = SetType.entries
        //when
        //then
        assertThat(setTypes).containsExactlyInAnyOrder(
            SetType.RUN,
            SetType.GROUP
        )
    }
    @Test
    fun ValidGroup() {
        //given
        val tiles = listOf<Tile>(
            Tile(TileColor.BLUE, TileNumber.FOUR),
            Tile(TileColor.RED, TileNumber.FOUR),
            Tile(TileColor.YELLOW, TileNumber.FOUR)
        )
        //when
        val result = Set.validate(tiles)
        //then
        assertThat(result).isTrue()
    }
    @Test
    fun ValidRun() {
        //given
        val tiles = listOf<Tile>(
            Tile(TileColor.BLUE, TileNumber.ONE),
            Tile(TileColor.BLUE, TileNumber.TWO),
            Tile(TileColor.BLUE, TileNumber.THREE)
        )
        //when
        val result = Set.validate(tiles)
        //then
        assertThat(result).isTrue()
    }


}
