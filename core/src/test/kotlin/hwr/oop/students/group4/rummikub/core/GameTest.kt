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
	// 
	}
	
}