package hwr.oop.examples.template
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.GameId
import hwr.oop.students.group4.rummikub.core.PlayerId
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import hwr.oop.ports.out.LoadGameByIdPort

@Disabled("Requires Docker")
@Testcontainers
class SqlPersistenceTest {
	
	companion object {
		@Container
		@JvmStatic
		val postgres = PostgreSQLContainer("postgres:17-alpine")
	}
	
	private lateinit var adapter: SqlPersistence
	private lateinit var dataSource: HikariDataSource
	
	@BeforeEach
	fun setUp() {
		val config = HikariConfig().apply {
			jdbcUrl = postgres.jdbcUrl
			username = postgres.username
			password = postgres.password
		}
		dataSource = HikariDataSource(config)
		adapter = SqlPersistence(dataSource)
	}
	
	@AfterEach
	fun tearDown() {
		if (::dataSource.isInitialized) {
			dataSource.close()
		}
	}

	@Test
	fun `save game and load game successfully`() {
		// given
		val gameId = GameId("11111111-1111-1111-1111-111111111111")
		val newGame = Game.createNewGame(
			gameId,
			listOf(PlayerId("player 1"), PlayerId("player 2"))
		)

		// when
		adapter.save(newGame)
		val loadedGame = adapter.loadById(gameId)

		// then
		assertThat(loadedGame).isEqualTo(newGame)
	}
	@Test
	fun `load game unsuccessfully`() {
		// given
		val gameId = GameId("fake Game ID")

		// when
		// then
		assertThatThrownBy { adapter.loadById(gameId) }
			.isInstanceOf(LoadGameByIdPort.CouldNotLoadException::class.java)
	}
}

