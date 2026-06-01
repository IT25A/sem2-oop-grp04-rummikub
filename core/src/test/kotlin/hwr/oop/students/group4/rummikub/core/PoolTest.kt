package hwr.oop.students.group4.rummikub.core

import org.junit.jupiter.api.TestInstance
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PoolTest {
    private lateinit var pool: Pool
    private lateinit var mutablePool: MutablePool
    private lateinit var playerList: List<PlayerId>

    //given
    @BeforeEach
    fun setUp() {
        playerList = listOf(PlayerId("player1"), PlayerId("player2"))
        pool = Pool.createShuffledPool()
    }

    @Test
    fun `pool without Joker contains 104 tiles`() {
        //when
        val tiles = pool.tiles()
        //then
        assertThat(tiles).hasSize(104)
    }


    @Test
    fun `pool without Joker contains each distinct tile twice`() {
        //when
        val poolTiles = pool.tiles()
        val distinct = poolTiles.distinct()
        //then
        assertThat(distinct).hasSize(52).allMatch { tile -> poolTiles.count { it == tile } == 2 }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `drawing from pool`(count: Int) {
        val mutablePool = pool.toMutablePool()
        val drawnTile : List<Tile> = mutablePool.draw(count)
        val updatedPool = mutablePool.toPool()

        assertThat(updatedPool.tiles()).hasSize(pool.tiles().size - count)
        assertThat(drawnTile).hasSize(count)
    }
}



