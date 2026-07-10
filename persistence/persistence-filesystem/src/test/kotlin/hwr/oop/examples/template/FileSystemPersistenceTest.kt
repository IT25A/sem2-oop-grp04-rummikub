package hwr.oop.examples.template

import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.PlayerId
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class FileSystemPersistenceTest {
	
	private val fakeFileSystem = FakeFileSystem()
	private val tempDir = "tmp/template-test".toPath()
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

	private val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")))
	private val gameId = game.id()

	@Test
	fun `load successful in filesystem`() {
		//when
		sut.save(game)
		val loaded = sut.loadById(gameId)

		//then
		assertThat(loaded).isEqualTo(game)
	}

	@Test
	fun `load game unsuccesful, exception thrown`() {
		assertThatThrownBy { sut.loadById(gameId) }
			.hasMessageContaining("Could not load game", gameId.toString())
	}

	}


