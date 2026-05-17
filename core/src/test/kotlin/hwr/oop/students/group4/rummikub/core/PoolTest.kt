package hwr.oop.students.group4.rummikub.core

import org.junit.jupiter.api.TestInstance
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PoolTest {
    //given
    val game = Game()

    @Test
    fun `pool without Joker contains 104 tiles`() {
        //when
        val tiles = game.pool().tiles()
        //then
        assertThat(tiles).hasSize(104)
    }

    @Test
    fun `pool without Joker contains each distinct tile twice`() {
        //when
        val tiles = game.pool().tiles()
        val distinct = tiles.distinct()
        //then
        assertThat(distinct).hasSize(52).allMatch { tile -> tiles.count { it == tile } == 2 }
    }
    @Test
    fun `drawing from pool`() {
        val game = Game()
        val beforeTiles = game.pool().tiles().toMutableList()
        val drawnTile = game.pool().draw()
        val afterTiles = game.pool().tiles().toMutableList()
        afterTiles.add(drawnTile)
        assertThat(beforeTiles).containsExactlyInAnyOrderElementsOf(afterTiles)
    }
}


