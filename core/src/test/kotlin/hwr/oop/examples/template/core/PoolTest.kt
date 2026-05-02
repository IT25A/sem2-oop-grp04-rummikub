package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PoolTest {
    //given
    private val pool = Pool.createShuffledPool()

    @Test
    fun `pool contains 106 tiles`() {
        //when
        val tiles = pool.tiles()
        //then
        assertThat(tiles).hasSize(106)
    }

    @Test
    fun `pool contains each distinct tile twice`(){
        //when
        val tiles = pool.tiles()
        val distinct = tiles.distinct()
        //then
        assertThat(distinct).hasSize(53).allMatch { tile -> tiles.count { it == tile } == 2 }

    }

    @Test
    fun `pool is shuffled`() {
        //when
        val tiles = pool.tiles()
        val sorted = tiles.sortedBy { it.number() }
        //then
        assertThat(sorted).isNotEqualTo(tiles)
    }
}