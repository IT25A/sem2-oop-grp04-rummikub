package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GameStateTests {
    @Test
    fun `all game statuses exist`() {
        // given
        val statuses = GameStatus.entries
        // when
        // then
        assertThat(statuses).containsExactlyInAnyOrder(
            GameStatus.IN_PROGRESS,
            GameStatus.FINISHED
        )
    }

    @Test
    fun`game state created successfully`(){
        val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")))
        val gameState = GameState.fromGame(game)

        assertTrue(gameState.gameId() == game.id())
        assertTrue(gameState.status() == game.status())
        assertTrue(gameState.currentPlayer() == game.currentPlayer())
        assertTrue(gameState.winner() == null)
        assertTrue(gameState.table() == game.table())
        assertTrue(gameState.racks() == game.racks())
        assertTrue(gameState.pool() == game.pool())
    }

}