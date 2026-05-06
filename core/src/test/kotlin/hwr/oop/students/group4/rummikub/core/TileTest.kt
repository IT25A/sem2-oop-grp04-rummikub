package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TileTest {
	fun provideTestData(): Iterable<Pair<TileNumber, TileColor>> {
		val testData = TileNumber.entries.flatMap { number ->
			TileColor.entries.map {
				number to it
			}
		}
		return testData.asIterable()
	}
	
	@ParameterizedTest
	@MethodSource("provideTestData")
	fun testTile(tileData: Pair<TileNumber, TileColor>) {
		// given
		val number = tileData.first
		val color = tileData.second
		// when
		val tile = Tile(number=number, color = color)
		// then
		assertThat(tile.number()).isEqualTo(number)
		assertThat(tile.color()).isEqualTo(color)
		}
	}