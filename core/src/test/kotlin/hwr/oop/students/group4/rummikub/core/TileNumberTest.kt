package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TileNumberTest {
	@Test
	fun verifyAllNumbers() {
		// given
		val correctNames = listOf(
			TileNumber.ONE,
			TileNumber.TWO,
			TileNumber.THREE,
			TileNumber.FOUR,
			TileNumber.FIVE,
			TileNumber.SIX,
			TileNumber.SEVEN,
			TileNumber.EIGHT,
			TileNumber.NINE,
			TileNumber.TEN,
			TileNumber.ELEVEN,
			TileNumber.TWELVE,
			TileNumber.THIRTEEN
		)
		val correctValues = (1..13).toList().toTypedArray()
		// when
		val allNumbers = TileNumber.entries
		// then
		assertThat(allNumbers).containsExactlyInAnyOrder(*correctNames.toTypedArray())
		assertThat(allNumbers.map{ it.value() }).containsExactlyInAnyOrder(*correctValues)
	}
}