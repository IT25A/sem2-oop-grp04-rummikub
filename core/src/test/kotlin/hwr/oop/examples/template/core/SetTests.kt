package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SetTests {
    //given
    private val player1 = PlayerId("player1")
    private val tiles = listOf(
        Tile(TileColor.BLACK, TileNumber.THREE ),
        Tile(TileColor.BLACK, TileNumber.FOUR ),
        Tile(TileColor.BLACK, TileNumber.FIVE ),
        Tile(TileColor.BLACK, TileNumber.SIX ),
        Tile(TileColor.RED, TileNumber.SEVEN ),
        Tile(TileColor.BLUE, TileNumber.SEVEN ),
        Tile(TileColor.YELLOW, TileNumber.SEVEN ),
        Tile(TileColor.BLACK, TileNumber.SEVEN ),
    )
    private val playerOneRack = Rack(player1, tiles, false)

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

    @Test
    fun `valid Group test` () {

    }

    @Test
    fun `create RUN from rack` () {

    }

    @Test
    fun `First turn has 30 points` () {

    }

    @Test
    fun `first turn does not have 30 points` () {

    }
}