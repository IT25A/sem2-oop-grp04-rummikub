package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestTileColor {
	@Test
	fun validColorsExist() {
		// given
		val tileColors = TileColor.entries
		// when
		
		// then
		assertThat(tileColors).containsExactlyInAnyOrder(
			TileColor.RED,
			TileColor.YELLOW,
			TileColor.BLUE,
			TileColor.BLACK
		)
	}
	
}