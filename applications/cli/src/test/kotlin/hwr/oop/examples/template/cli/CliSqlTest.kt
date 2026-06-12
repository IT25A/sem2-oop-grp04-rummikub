package hwr.oop.examples.template.cli

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import hwr.oop.examples.template.SqlPersistence
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
@Disabled
@Testcontainers
class CliSqlTest {
	
	companion object {
		@Container
		@JvmStatic
		val postgres = PostgreSQLContainer("postgres:17-alpine")
	}
	
	private lateinit var persistence: SqlPersistence
	private lateinit var dataSource: HikariDataSource
	private lateinit var sut: ExampleBaseCommand
	
	@BeforeEach
	fun setUp() {
		val config = HikariConfig().apply {
			jdbcUrl = postgres.jdbcUrl
			username = postgres.username
			password = postgres.password
		}
		dataSource = HikariDataSource(config)
		persistence = SqlPersistence(dataSource)
		sut = ExampleBaseCommand()
	}
	
	@AfterEach
	fun tearDown() {
		dataSource.close()
	}
	
	@Test
	fun `do nothing`() {
		// given
		// when
		// then
	}
}
