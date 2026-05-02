package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class FillingRacksTest {
    //given
    private val player1 = PlayerId("player1")
    private val player2 = PlayerId("player2")
    private val player3 = PlayerId("player3")
    private val player4 = PlayerId("player4")

    companion object {
        private val player1 = PlayerId("player1")
        private val player2 = PlayerId("player2")
        private val player3 = PlayerId("player3")
        private val player4 = PlayerId("player4")

        @JvmStatic
        fun playerCombinations(): Stream<List<PlayerId>> = Stream.of(
            listOf(player1, player2),
            listOf(player1, player2, player3),
            listOf(player1, player2, player3, player4)
        )
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `new Game, Players all have 14 tiles in Rack`(players: List<PlayerId>) {
        val game = Game.create(players = players)
        //when
        val racks = players.map { player -> game.rackOf(player) }
        //then
        assertThat(racks).hasSize(players.size).allMatch { it.tiles().size == 14 }
    }
}