package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameTests {

    companion object {
        private val player1 = PlayerId("player1")
        private val player2 = PlayerId("player2")
        private val player3 = PlayerId("player3")
        private val player4 = PlayerId("player4")

        private const val POOL_SIZE_WITH_JOKER      = 106
        private const val POOL_SIZE_WITHOUT_JOKER   = 104
        private const val TILES_PER_PLAYER          = 14

        @JvmStatic
        fun playerCombinations(): Stream<List<PlayerId>> = Stream.of(
            listOf(player1, player2),
            listOf(player1, player2, player3),
            listOf(player1, player2, player3, player4)
        )
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `new Game with Joker, Players all have 14 tiles in Rack`(players: List<PlayerId>) {
        val game = Game.createNewGame(players = players, withJoker = true)
        //when
        val racks = players.map { game.rackOf(it) }
        //then
        assertThat(racks).hasSize(players.size).allMatch { it.tiles().size == TILES_PER_PLAYER }
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `new Game without Joker, Players all have 14 tiles in Rack`(players: List<PlayerId>) {
        val game = Game.createNewGame(players = players, withJoker = false)
        //when
        val racks = players.map { game.rackOf(it) }
        //then
        assertThat(racks).hasSize(players.size).allMatch { it.tiles().size == TILES_PER_PLAYER }
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `new Game with Joker, Pool has appropriate amount of tiles leftover`(players: List<PlayerId>) {
        val game = Game.createNewGame(players = players, withJoker = true)
        //when
        val poolSize = game.pool().tiles().size
        //then
        assertThat(poolSize).isEqualTo(POOL_SIZE_WITH_JOKER - (players.size * TILES_PER_PLAYER))
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `new Game without Joker, Pool has appropriate amount of tiles leftover`(players: List<PlayerId>) {
        val game = Game.createNewGame(players = players, withJoker = false)
        //when
        val poolSize = game.pool().tiles().size
        //then
        assertThat(poolSize).isEqualTo(POOL_SIZE_WITHOUT_JOKER - (players.size * TILES_PER_PLAYER))
    }
}