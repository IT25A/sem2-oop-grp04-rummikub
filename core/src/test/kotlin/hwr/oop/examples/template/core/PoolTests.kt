package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PoolTests {
    //given
    private val pool = Pool.createShuffledPool()

    @Test
    fun `pool contains 104 tiles`() {
        //when
        val tiles = pool.tiles()
        //then
        assertThat(tiles).hasSize(104)
    }

    @Test
    fun `pool each distinct tile twice`(){
        //when
        val tiles = pool.tiles()
        val distinct = tiles.distinct()
        //then
        assertThat(distinct).hasSize(52).allMatch { tile -> tiles.count { it == tile } == 2 }

    }

    @Test
    fun `pool without Joker is shuffled`() {
        //when
        val tiles = pool.tiles()
        val sorted = tiles.sortedBy { it.number().points() }
        //then
        assertThat(sorted).isNotEqualTo(tiles)
    }
}