package hwr.oop.students.group4.rummikub.core

import org.junit.jupiter.api.TestInstance
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PoolTest {
    private lateinit var game: Game
    private lateinit var playerList: List<PlayerId>
    
    //given
    @BeforeEach
    fun setUp() {
        playerList = listOf(PlayerId("player1"), PlayerId("player2"))
        game = Game.createNewGame(playerList)
    }

    @Test
    fun `pool without Joker contains `() {
        //when
        val tiles = game.pool().tiles()
        val rackTiles = game.racks().flatMap{ rack -> rack.tiles()}
        //then
        assertThat(tiles).hasSize(104 - rackTiles.size)
    }

    
    @Test
    fun `pool without Joker contains each distinct tile twice`() {
        //when
        val poolTiles = game.pool().tiles()
        val rackTiles = game.racks().flatMap{ rack -> rack.tiles()}
        val tiles = poolTiles + rackTiles
        val distinct = tiles.distinct()
        //then
        assertThat(distinct).hasSize(52).allMatch { tile -> tiles.count { it == tile } == 2 }
    }
    @Test
    fun `drawing from pool`() {
        val beforeTiles = game.pool().tiles().toMutableList()
        val drawnTile = game.pool().draw(1)
        val afterTiles = game.pool().tiles().toMutableList()
        afterTiles.addAll(drawnTile)
        assertThat(beforeTiles).containsExactlyInAnyOrderElementsOf(afterTiles)
    }
}


