package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TileTest {
	@Test
	fun `red seven exists`() {
		// given
		// when
		val testTile = Tile(number = 7, color = TileColor.RED)
		// then
		assertThat(testTile.number()).isEqualTo(7)
		assertThat(testTile.color()).isEqualTo(TileColor.RED)
	}
}