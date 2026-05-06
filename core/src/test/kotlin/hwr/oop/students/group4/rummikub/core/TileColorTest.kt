package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TileColorTest {
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