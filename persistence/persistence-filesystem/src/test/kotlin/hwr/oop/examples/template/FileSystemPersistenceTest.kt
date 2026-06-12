package hwr.oop.examples.template

import hwr.oop.examples.template.core.Game
import hwr.oop.examples.template.core.GameState
import hwr.oop.examples.template.core.PlayerId
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class FileSystemPersistenceTest {
	
	private val fakeFileSystem = FakeFileSystem()
	private val tempDir = "/tmp/template-test".toPath()
	private val sut: FileSystemPersistence
	
	init {
		fakeFileSystem.createDirectories(tempDir)
		sut = FileSystemPersistence(
			FileSystemPersistenceConfiguration(tempDir),
			fakeFileSystem
		)
	}
	
	@AfterEach
	fun tearDown() {
		fakeFileSystem.checkNoOpenFiles()
	}
	
	@Test
	fun `save game and load game successful`() {
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")));
		val gameID = game.id()
		// when
		sut.save(GameState.fromGame(game));
		val savedGameState = sut.load(gameID)
		val savedGame = Game.loadGame(savedGameState)
		// then
		assertThat(savedGame).isEqualTo(game)
	}

	@Test
	fun `load game unsuccessful`() {
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")));
		// when
		sut.save(GameState.fromGame(game));
		// then
		assertThatThrownBy {sut.load("trollId")}.hasMessageContaining("Game not found: trollId")
	}

	@Test
	fun `load game unsuccessful, missing game id`() {
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")));
		// when
		sut.save(GameState.fromGame(game));
		// then
		assertThatThrownBy {sut.load(null)}.hasMessageContaining("Game ID must be specified")
	}
}

