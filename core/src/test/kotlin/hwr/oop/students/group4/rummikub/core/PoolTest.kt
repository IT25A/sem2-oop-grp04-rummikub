package hwr.oop.students.group4.rummikub.core

import org.junit.jupiter.api.TestInstance
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PoolTest {
    //given
    val game = Game.generateShuffledDeck()
    val pool = game.pool()

    @Test
    fun `pool without Joker contains 104 tiles`() {
        //when
        val tiles = pool.tiles()
        //then
        assertThat(tiles).hasSize(104)
    }

    @Test
    fun `pool without Joker contains each distinct tile twice`(){
        //when
        val tiles = pool.tiles()
        val distinct = tiles.distinct()
        //then
        assertThat(distinct).hasSize(52).allMatch { tile -> tiles.count { it == tile } == 2 }

    }
}


