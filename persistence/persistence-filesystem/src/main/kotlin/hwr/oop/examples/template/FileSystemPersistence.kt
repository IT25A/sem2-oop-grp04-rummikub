package hwr.oop.examples.template

import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.GameId
import hwr.oop.ports.out.GameRepository
import hwr.oop.ports.out.LoadGameByIdPort
import hwr.oop.ports.out.SaveGamePort
import kotlinx.serialization.json.Json
import okio.FileNotFoundException
import okio.FileSystem
import okio.Path

private val json = Json {
	prettyPrint = true
	ignoreUnknownKeys = true
}

class FileSystemPersistence(
	configuration: FileSystemPersistenceConfiguration,
	private val fileSystem: FileSystem = FileSystem.SYSTEM,
) : GameRepository, SaveGamePort {

	private val directory = configuration.directory

	private fun path(gameId: GameId): Path {
		return directory / "${gameId.value}.json"
	}

	override fun save(game: Game) {
		val gameId = game.id()
		val path = path(gameId)
		fileSystem.write(path) {
			writeUtf8(json.encodeToString<Game>(game))
		}
	}

	override fun loadById(gameId: GameId): Game {
		val path = path(gameId)
		val readString = try {
			fileSystem.read(path) {
				readUtf8()
			}
		} catch (e: FileNotFoundException) {
			throw LoadGameByIdPort.CouldNotLoadException(gameId, e)
		}
		return json.decodeFromString<Game>(readString)
	}
}

