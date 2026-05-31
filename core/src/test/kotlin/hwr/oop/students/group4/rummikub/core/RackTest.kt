package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RackTest {
    private val player: PlayerId = PlayerId("player1")
    private lateinit var tiles: List<Tile>

    @BeforeEach
    fun setUp() {
        tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.ONE),
            Tile(TileColor.BLUE, TileNumber.TWO),
            Tile(TileColor.BLUE, TileNumber.THREE)
        )
    }

    @Test
    fun `rack owner exists`() {
        // when
        val rack = Rack(player, tiles)

        // then
        assertThat(rack.owner().toString()).isEqualTo("PlayerId(playerId=player1)")
        assertThat(rack.tiles()).containsExactlyInAnyOrderElementsOf(tiles)
    }

    @Test
    fun`rack owner is not melded`() {
        //when
        val rack = Rack(player, tiles)
        //then
        assertThat(rack.melded()).isFalse()
    }

    @Test
    fun`remove tiles from rack successful`() {
        //given
        val rack = Rack(player, tiles)
        val firstTiles = (1..3).map {
            tiles.toMutableList().removeFirst()
        }
        //when
        rack.removeTiles(firstTiles)
        //then
        assertThat(rack.melded()).isTrue
        assertThat(rack.tiles()).containsExactlyInAnyOrderElementsOf(tiles)
    }

    @Test
    fun `add tiles to rack`() {
        //given
        val rack = Rack(player, tiles)
        val tilesToAdd = listOf(Tile(TileColor.BLUE, TileNumber.FOUR))

        val mutableTilesList = tiles.toMutableList()
        mutableTilesList.addAll(tilesToAdd)
        // when
        val newRack = rack.addTiles(tilesToAdd)

        //then
        assertThat(newRack.tiles()).containsExactlyInAnyOrderElementsOf(mutableTilesList)
    }
}