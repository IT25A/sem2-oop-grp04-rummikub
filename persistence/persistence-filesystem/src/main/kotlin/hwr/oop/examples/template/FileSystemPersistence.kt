package hwr.oop.examples.template

import hwr.oop.examples.template.core.GameRepository
import hwr.oop.examples.template.core.GameState
import kotlinx.serialization.json.Json
import okio.FileSystem
import kotlin.io.path.exists

class FileSystemPersistence(
	configuration: FileSystemPersistenceConfiguration,
	private val fileSystem: FileSystem = FileSystem.SYSTEM,
) : GameRepository {
	private val directory = configuration.directory.toFile().also { it.mkdirs() }

	override fun load(gameId: String?): GameState {
		requireNotNull(gameId) { "Game ID must be specified" }
		val file = directory.resolve("$gameId.json")
		require(file.exists()) { "Game not found: $gameId" }
		return AppJson.decodeFromString(file.readText())
	}

	override fun save(gameState: GameState) {
		val file = directory.resolve("${gameState.gameId()}.json")
		file.writeText(Json.encodeToString(gameState))
	}
}

