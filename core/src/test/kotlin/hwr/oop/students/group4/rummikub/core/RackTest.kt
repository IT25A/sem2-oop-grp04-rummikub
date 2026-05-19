package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RackTest {
    private val player: PlayerId = PlayerId("player1")
    private lateinit var pool: Pool
    private lateinit var tiles: MutableList<Tile>

    @BeforeEach
    fun setUp() {
        // given
        pool = Pool()
        tiles = (1..14).map { pool.draw() }.toMutableList()
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
            tiles.removeFirst()
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
        val tilesToAdd = (1..3).map {pool.draw() }
        tiles.addAll(tilesToAdd)
        // when
        rack.addTiles(tilesToAdd)

        //then
        assertThat(rack.tiles()).containsExactlyInAnyOrderElementsOf(tiles)
    }
}