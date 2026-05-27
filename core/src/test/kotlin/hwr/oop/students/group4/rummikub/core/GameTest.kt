package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameTest {
	
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
	
	@ParameterizedTest
	@MethodSource("streamValidPlayers")
	fun `create game valid`(validPlayers: List<PlayerId>) {
		// given
		val players = validPlayers
		// when
		val gameObject = Game.createNewGame(players)
		
		//then
		assertThat(gameObject.players()).containsExactlyInAnyOrder(*players.toTypedArray())
	}
	
	@Test
	fun `there is only one real anton`() {
		// given
		val players = listOf("Anton❤️", "Anton❤️", "Boas").map { PlayerId(it) }
		// w/t -hen
		assertThatThrownBy { Game.createNewGame(players) }
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Players must have different names")
	}
	// please add assertion tests for the require statements...
	@Test
	fun `player drawing is not part of game`() {
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")))
		val intrudingPlayers = PlayerId("hacker")
		// w/t -hen
		assertThatThrownBy { game.drawTile(intrudingPlayers) }
			.isInstanceOf(IllegalArgumentException::class.java)
	}
	
	
	// please put in sepearate test class
	@Test
	fun `draw tile out of turn`() {
		// given
		val firstPlayer = PlayerId("Ricardo")
		val secondPlayer = PlayerId("Melvin")
		//when
		val gameObject = Game.createNewGame(listOf(firstPlayer, secondPlayer))
		// then
		assertThatThrownBy { gameObject.drawTile(secondPlayer) }
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Its not ${secondPlayer.playerId()}'s turn")
	}
	
	@Test
	fun `draw tile but pool is empty`() {
		// given
		val gameObject = Game(
			pool = Pool(
				tiles = mutableListOf(),
			),
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
		assertThatThrownBy { gameObject.drawTile(PlayerId("player1")) }
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Pool is empty")
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

	@Test
	fun `draw tile into Rack`() {
		// given
		val oldGame = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")))
		val tileToBeDrawn = oldGame.pool().tiles().first() // we always draw the first tile -> this is the card which gets drawn
		val sizeOfOldPool = oldGame.pool().tiles().size
		// when
		val newGame = oldGame.drawTile(PlayerId("player1"))
		val newPlayerRack = newGame.rackOfPlayer(PlayerId("player1"))
		val sizeOfNewPool = newGame.pool().tiles().size
		// then
		assertThat(newPlayerRack.tiles()).contains(tileToBeDrawn)
		assertThat(sizeOfOldPool).isEqualTo(sizeOfNewPool+1)
	}
	
}

