package hwr.oop.students.group4.rummikub.core

import hwr.oop.students.group4.rummikub.core.Game.Companion.createNewGame
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
		assertThatThrownBy{Game.createNewGame(players)}
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Rummikub is always 2-4")
	}
	@Test
	fun `create game invalid, too many players`() {
		// given
		val players = listOf("Elissar", "Melvin", "Ricardo", "Anton", "Boas" ).map { PlayerId(it) }
		// when
		//then
		assertThatThrownBy{Game.createNewGame(players)}
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Rummikub is always 2-4")
	}
	
	companion object {
		@JvmStatic
		fun streamValidPlayers(): Stream<List<PlayerId>>{
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
		val players = listOf("Anton❤️", "Anton❤️", "Boas" ).map { PlayerId(it) }
		// w/t -hen
		assertThatThrownBy{Game.createNewGame(players)}
			.isInstanceOf(IllegalArgumentException::class.java)
			.hasMessageContaining("Players must have different names" )
	}
	
	@Test
	fun `draw tile`() {
	    // given

        // when

        // then
	}
	
	@Test
	fun `player drawing is not part of game`(){
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")))
		val intrudingPlayers = PlayerId("hacker")
		// w/t -hen
		assertThatThrownBy { game.drawTile(intrudingPlayers) }
			.isInstanceOf(IllegalArgumentException::class.java)
	}
	
	@Test
	fun `draw tile out of turn`() {
		// given
		val firstPlayer = PlayerId("Ricardo")
		val secondPlayer = PlayerId("Melvin")
		//when
		val gameObject = Game.createNewGame(listOf(firstPlayer, secondPlayer))
		// then
		assertThatThrownBy{gameObject.drawTile(secondPlayer)}
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
					playerId=PlayerId("player1"),
					tiles=mutableListOf()
				),
				Rack(
					playerId = PlayerId("player2"),
					tiles=mutableListOf()
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
	fun `getting rack of player that is nonexistant`(){
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")))
		// when
		val intrudingPlayers = PlayerId("hacker")
		//then
		assertThat(game.rackOfPlayer(intrudingPlayers)).isNull()
	}
	
	@Test
	// this test works but rackOfPlayer being Nullable is really shitty, pls replace with exception!
	// TODO change implementation of rackOfPlayers()
	fun `draw tile but everything works`(){
		// given
		val player = PlayerId("player1")
		val oldGame = createNewGame(listOf(player, PlayerId("player2")))
		// when
		val newGame =  oldGame.drawTile(player)
		val drawnTile = newGame.rackOfPlayer(player)?.tiles()?.toMutableList()
		drawnTile?.removeAll(oldGame.rackOfPlayer(player)?.tiles()?.toList() ?: listOf() )
		
		// then
		assertThat(newGame.pool().tiles()).containsExactlyInAnyOrderElementsOf((oldGame.pool().tiles()-drawnTile) as Iterable<Tile?>?) // this cast is needed because of rackOfPlayer being nullable
		assertThat(
			newGame.rackOfPlayer(player)
				?.tiles())
				.containsExactlyInAnyOrderElementsOf(
					(oldGame.rackOfPlayer(player)
						?.tiles()
						?.plus(
							drawnTile?.first() ?: mutableListOf<Tile>()
						)as Iterable<Tile?>?)
				)
		// currentPlayerIndex cannot be validated, because there is no .get()-Method available
	}
	
	
}