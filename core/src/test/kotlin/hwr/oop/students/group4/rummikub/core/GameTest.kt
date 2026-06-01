package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameTest {

	companion object {
		@JvmStatic
		fun streamValidPlayers(): Stream<List<PlayerId>> {
			return Stream.of(
				listOf(PlayerId("player1"), PlayerId("player2")),
				listOf(PlayerId("player1"), PlayerId("player2"), PlayerId("player3")),
				listOf(PlayerId("player1"), PlayerId("player2"), PlayerId("player3"), PlayerId("player4")),
			)
		}
	}

	@Test
	fun `create game invalid, only one player`() {
		// given
		val players = listOf(PlayerId("lonelyGamer"))
		// when
		//then
		assertThatThrownBy { Game.createNewGame(players) }
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Rummikub is always 2-4")
	}
	
	@Test
	fun `create game invalid, too many players`() {
		// given
		val players = listOf("Elissar", "Melvin", "Ricardo", "Anton", "Boas").map { PlayerId(it) }
		// when
		//then
		assertThatThrownBy { Game.createNewGame(players) }
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Rummikub is always 2-4")
	}

	@Test
	fun `create game invalid, name repeated`() {
		// given
		val players = listOf("Anton❤️", "Anton❤️", "Boas").map { PlayerId(it) }
		// w/t -hen
		assertThatThrownBy { Game.createNewGame(players) }
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Players must have different names")
	}

	@ParameterizedTest
	@MethodSource("streamValidPlayers")
	fun `create game valid`(validPlayers: List<PlayerId>) {
		// given
		val players = validPlayers
		// when
		val game = Game.createNewGame(players)
		
		//then
		assertThat(game.players()).containsExactlyInAnyOrder(*players.toTypedArray())
		game.racks().forEach { assertThat(it.tiles()).hasSize(14) }
		assertThat(game.pool().tiles()).hasSize(104 - 14 * players.size)
	}

	@ParameterizedTest
	@MethodSource("streamValidPlayers")
	fun `player drawing is not part of game`(validPlayers: List<PlayerId>) {
		// given
		val game = Game.createNewGame(validPlayers)
		val intrudingPlayer = PlayerId("intruder")
		// w/t -hen
		assertThatThrownBy { game.drawTile(intrudingPlayer) }
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Player is not in this game")
	}

	@ParameterizedTest
	@MethodSource("streamValidPlayers")
	fun `player drawing is out of turn`(validPlayers: List<PlayerId>) {
		// given
		val secondPlayer = validPlayers[1]
		//when
		val gameObject = Game.createNewGame(validPlayers)
		// then
		assertThatThrownBy { gameObject.drawTile(secondPlayer) }
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Its not ${secondPlayer.playerId()}'s turn")
	}

	@Test
	fun `draw tile but pool is empty`() {
		// given
		val game = Game(
			pool = Pool( listOf()),
			rackOfPlayers = listOf(
				Rack(
					playerId = PlayerId("player1"),
					tiles = mutableListOf()
				),
				Rack(
					playerId = PlayerId("player2"),
					tiles = mutableListOf()
				)
			),
			currentPlayerIndex = 0,
			currentPlayer = PlayerId("player1")
		)
		
		// w/t -hen
		assertThatThrownBy { game.drawTile(PlayerId("player1")) }
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Pool is empty")
	}

	@ParameterizedTest
	@MethodSource("streamValidPlayers")
	fun `drawing tile is successful`(validPlayers: List<PlayerId>) {
		// given
		val game = Game.createNewGame(validPlayers)
		val pool = game.pool()
		val tileToBeDrawn = pool.tiles().first()


		val newGame = game.drawTile(game.currentPlayer())
		val newPlayerRack = newGame.rackOfPlayer(game.currentPlayer())
		// then
		assertThat(newPlayerRack.tiles()).contains(tileToBeDrawn)
		assertThat(newGame.pool().tiles()).hasSize(pool.tiles().size - 1)
	}

	@Test
	fun `getting rack of player that is not there`() {
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")))
		// when
		val intrudingPlayers = PlayerId("hacker")
		//then
		assertThatThrownBy{game.rackOfPlayer(intrudingPlayers)}.hasMessageContaining("Player is not in this game")
	}
}

