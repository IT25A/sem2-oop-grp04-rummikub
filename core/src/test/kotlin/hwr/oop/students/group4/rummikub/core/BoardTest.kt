package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

	@Test
	fun `add sets to board`()	{
		// given
		val oldGame = Game.createNewGame(
			listOf(
				PlayerId("Anton"),
				PlayerId("Melvin")
			)
		)
		
		val playingPlayer = oldGame.currentPlayer()
		
		val setToBeAdded = Set(listOf(
			Tile(color= TileColor.RED, number = TileNumber.ONE),
			Tile(color= TileColor.RED, number = TileNumber.TWO),
			Tile(color= TileColor.RED, number = TileNumber.THREE),
			),
		)
		setToBeAdded.type() //is needed to initialize type.
		val setList = listOf(setToBeAdded)
		// when
		val newGame = oldGame.addSet(newSets = setList, player = playingPlayer)
		
		// then
		assertThat(newGame.board()).contains(setToBeAdded)
		assertThat(newGame.rackOfPlayer(playingPlayer).tiles())
			.containsExactlyInAnyOrderElementsOf(
				oldGame.rackOfPlayer(playingPlayer).tiles()-setToBeAdded.tiles()
			)
	}
	

}