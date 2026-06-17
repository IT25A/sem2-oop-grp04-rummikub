package hwr.oop.examples.template

import com.zaxxer.hikari.HikariDataSource
import hwr.oop.examples.template.core.AppJson
import hwr.oop.examples.template.core.GameRepository
import hwr.oop.examples.template.core.GameState
import liquibase.Liquibase
import liquibase.Scope
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.logging.core.NoOpLogService
import liquibase.resource.ClassLoaderResourceAccessor
import liquibase.ui.LoggerUIService
import org.jetbrains.exposed.v1.jdbc.Database
import javax.sql.DataSource

class SqlPersistence(private val dataSource: DataSource) : GameRepository {
	
	constructor(jdbcUrl: String, username: String, password: String) : this(
		HikariDataSource().apply {
			setJdbcUrl(jdbcUrl)
			setUsername(username)
			setPassword(password)
		}
	)
	
	init {
		runLiquibaseMigrations()
		Database.connect(dataSource)
	}
	
	private fun runLiquibaseMigrations() {
		System.setProperty("liquibase.command.update.showSummary", "OFF")
		val scopeAttrs = mapOf(
			Scope.Attr.logService.name to NoOpLogService(),
			Scope.Attr.ui.name to LoggerUIService(),
		)
		Scope.child(scopeAttrs) {
			dataSource.connection.use { connection ->
				val database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(JdbcConnection(connection))
				Liquibase(
					"db/changelog/db.changelog-master.yaml",
					ClassLoaderResourceAccessor(),
					database
				).update("")
			}
		}
	}

	override fun load(gameId: String?): GameState {
		require(gameId != null) { "Game Id must not be null" }
		require(gameId.isNotEmpty()) { "Game Id must not be empty" }
		require(gameId.isNotBlank()) { "Game Id must not be blank" }

		dataSource.connection.use { connection ->
			connection.prepareStatement(
				"""
				SELECT game FROM game_states WHERE id = ?
				""".trimIndent()
			).use { preparedStatement ->
				preparedStatement.setString(1, gameId)
				val response = preparedStatement.executeQuery()
				check(response.next()) { "Game not found: $gameId" }
				return AppJson.decodeFromString(response.getString("game"))
			}
		}
	}

	override fun save(gameState: GameState) {
		val json = AppJson.encodeToString(gameState)
		dataSource.connection.use { connection ->
			connection.prepareStatement(
				"""
				INSERT INTO game_states (id, game) VALUES (?, ?::jsonb)
				ON CONFLICT (id) DO UPDATE SET game = EXCLUDED.game
				""".trimIndent()
			).use { preparedStatement ->
				preparedStatement.setString(1, gameState.gameId())
				preparedStatement.setString(2, json)
				preparedStatement.executeUpdate()
			}
		}
	}
}

