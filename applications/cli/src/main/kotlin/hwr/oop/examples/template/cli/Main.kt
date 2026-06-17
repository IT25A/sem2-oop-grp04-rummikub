package hwr.oop.examples.template.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import hwr.oop.examples.template.FileSystemPersistence
import hwr.oop.examples.template.FileSystemPersistenceConfiguration
import hwr.oop.examples.template.SqlPersistence
import hwr.oop.examples.template.config.AppConfig
import hwr.oop.examples.template.config.ConfigLoader
import hwr.oop.examples.template.config.PersistenceType
import kotlinx.serialization.json.Json
import okio.Path.Companion.toPath

class ExampleBaseCommand : CliktCommand(name = "example") {
	override fun run() = Unit
}

fun main(args: Array<String>) {
	val appConfig = ConfigLoader.load()
	val persistence = buildPersistence(appConfig)
	val json = Json{ prettyPrint = true}

	ExampleBaseCommand()
		.subcommands(
			StartGameCommand(),
			OnGameIdCommand().subcommands(
				GetGameCommand(),
				PlayTilesCommand(),
				DrawTileCommand(),
			),
		)
		.main(args)
}

private fun buildPersistence(appConfig: AppConfig): Any {
	return when (appConfig.persistence) {
		PersistenceType.SQL -> SqlPersistence(
			appConfig.sql.jdbcUrl,
			appConfig.sql.username,
			appConfig.sql.password,
		)
		
		PersistenceType.FILE_SYSTEM -> FileSystemPersistence(
			configuration = FileSystemPersistenceConfiguration(
				directory = appConfig.fileSystem.directory.toPath()
			)
		)
	}
}

