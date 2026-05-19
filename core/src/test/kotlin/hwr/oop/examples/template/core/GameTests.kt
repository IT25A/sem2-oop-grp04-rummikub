package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class GameTests {

    companion object {
        private val player1 = PlayerId("player1")
        private val player2 = PlayerId("player2")
        private val player3 = PlayerId("player3")
        private val player4 = PlayerId("player4")

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
    @ValueSource(ints = [0, 1, 5])
    fun `invalid number of players, exception` (invalidInt: Int){
        //when
        val players: List<PlayerId> = (1..invalidInt).map { PlayerId("player$it") }
        //then
        assertThatThrownBy {
            Game.createNewGame(
                players = players,
            )
        }.hasMessageContaining("has to have 2-4 players")
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `new Game without Joker, dealing of tiles correctly`(players: List<PlayerId>) {
        val game = Game.createNewGame(players = players)
        //when
        val racks = players.map { game.rackOf(it) }
        val poolSize = game.pool().tiles().size
        //then
        assertThat(racks).hasSize(players.size).allMatch { it.tiles().size == TILES_PER_PLAYER }
        assertThat(poolSize).isEqualTo(POOL_SIZE_WITHOUT_JOKER - (players.size * TILES_PER_PLAYER))
    }

    @Test
    fun `Exception for players using same name` () {
        //given
        val trollPlayers = listOf(PlayerId("troll"), PlayerId("troll"))
        //when
        //then
        assertThatThrownBy {Game.createNewGame(trollPlayers)}.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {Game.createNewGame(trollPlayers)}.hasMessageContaining("unique names")

    }
}