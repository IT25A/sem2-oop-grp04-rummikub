package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PoolTests {
    //given
    private val poolWithJoker = Pool.createShuffledPool(true)
    private val poolWithoutJoker = Pool.createShuffledPool(false)

    @Test
    fun `pool with Joker contains 106 tiles`() {
        //when
        val tiles = poolWithJoker.tiles()
        //then
        assertThat(tiles).hasSize(106)
    }

    @Test
    fun `pool without Joker contains 104 tiles`() {
        //when
        val tiles = poolWithoutJoker.tiles()
        //then
        assertThat(tiles).hasSize(104)
    }

    @Test
    fun `pool with Joker contains each distinct tile twice`(){
        //when
        val tiles = poolWithJoker.tiles()
        val distinct = tiles.distinct()
        //then
        assertThat(distinct).hasSize(53).allMatch { tile -> tiles.count { it == tile } == 2 }
    }

    @Test
    fun `pool without Joker contains each distinct tile twice`(){
        //when
        val tiles = poolWithoutJoker.tiles()
        val distinct = tiles.distinct()
        //then
        assertThat(distinct).hasSize(52).allMatch { tile -> tiles.count { it == tile } == 2 }

    }

    @Test
    fun `pool without Joker, contains no Joker`(){
        val tiles = poolWithoutJoker.tiles()
        val jokers = tiles.filter { it.number() == TileNumber.JOKER && it.color() == TileColor.JOKER }
        //then
        assertThat(jokers).isEmpty()
    }

    @Test
    fun `pool with Joker is shuffled`() {
        //when
        val tiles = poolWithJoker.tiles()
        val sorted = tiles.sortedBy { it.number() }
        //then
        assertThat(sorted).isNotEqualTo(tiles)
    }

    @Test
    fun `pool without Joker is shuffled`() {
        //when
        val tiles = poolWithoutJoker.tiles()
        val sorted = tiles.sortedBy { it.number() }
        //then
        assertThat(sorted).isNotEqualTo(tiles)
    }
}