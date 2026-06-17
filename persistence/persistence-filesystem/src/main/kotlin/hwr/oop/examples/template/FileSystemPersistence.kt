package hwr.oop.examples.template

import hwr.oop.examples.template.core.AppJson
import hwr.oop.examples.template.core.GameRepository
import hwr.oop.examples.template.core.GameState
import kotlinx.serialization.json.Json
import okio.FileSystem

class FileSystemPersistence(
	configuration: FileSystemPersistenceConfiguration,
	private val fileSystem: FileSystem = FileSystem.SYSTEM,
) : GameRepository {
	private val directory = configuration.directory.toFile().also { it.mkdirs() }

	override fun load(gameId: String?): GameState {
		require(gameId != null) { "Game Id must not be null" }
		require(gameId.isNotEmpty()) { "Game Id must not be empty" }
		require(gameId.isNotBlank()) { "Game Id must not be blank" }

		val file = directory.resolve("$gameId.json")
		require(file.exists()) { "Game not found: $gameId" }
		return AppJson.decodeFromString(file.readText())
	}

	override fun save(gameState: GameState) {
		val file = directory.resolve("${gameState.gameId()}.json")
		file.writeText(Json.encodeToString(gameState))
	}
}

