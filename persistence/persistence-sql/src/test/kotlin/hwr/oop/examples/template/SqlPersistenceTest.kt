package hwr.oop.examples.template

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import hwr.oop.examples.template.core.Game
import hwr.oop.examples.template.core.GameState
import hwr.oop.examples.template.core.PlayerId
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class SqlPersistenceTest {
	
	companion object {
		@Container
		@JvmStatic
		val postgres = PostgreSQLContainer("postgres:17-alpine")
	}
	
	private lateinit var adapter: SqlPersistence
	private lateinit var dataSource: HikariDataSource
	private lateinit var game: Game
	
	@BeforeEach
	fun setUp() {
		val config = HikariConfig().apply {
			jdbcUrl = postgres.jdbcUrl
			username = postgres.username
			password = postgres.password
		}
		dataSource = HikariDataSource(config)
		adapter = SqlPersistence(dataSource)
		game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")))
	}
	
	@AfterEach
	fun tearDown() {
		if (::dataSource.isInitialized) {
			dataSource.close()
		}
	}
	
	@Test
	fun `save game and load game successful`() {
		// given
		val gameState = GameState.fromGame(game)
		val gameId = game.id()
		// when
		adapter.save(gameState)
		val savedGameState = adapter.load(gameId)
		// then
		assertThat(savedGameState).isEqualTo(gameState)
	}
	@Test
	fun `load game unsuccessful, missing game id`() {
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")));
		// when
		adapter.save(GameState.fromGame(game));
		// then
		assertThatThrownBy {adapter.load(null)}.hasMessageContaining("Game ID must be specified")
	}

	@Test
	fun `load game unsuccessful`() {
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")));
		// when
		adapter.save(GameState.fromGame(game));
		// then
		assertThatThrownBy {adapter.load("trollId")}.hasMessageContaining("Game not found: trollId")
	}
	
}

