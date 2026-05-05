package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

class TestTile {
	@ParameterizedTest
	@EnumSource(TileNumber::class)
	fun testTile(number: TileNumber) {
		for (color in TileColor.entries) { // please provide Feedback whether it is okay to just use the enum classes, since they are already tested and in authorized changes in sourcecode are already caught by TestTileColor and TestTileNumber
			// given
			
			// when
			val tile = Tile(number = number, color = color)
			// then
			assertThat(tile.number()).isEqualTo(number)
			assertThat(tile.color()).isEqualTo(color)
		}
	}
	
	// is only one combination, should be fine tho right?
	@Test
	fun `test toString()`() {
		// given
		val instance = Tile(TileColor.RED, TileNumber.THIRTEEN)
		// when
		val toStringOutput = instance.toString()
		// then
		assertThat(toStringOutput).isEqualTo("Tile(color=RED, number=THIRTEEN)")
	}
	
	@Test
	fun `test equals()`() {
		// given
		val instance1 = Tile(TileColor.RED, TileNumber.ONE)
		val instance2 = Tile(TileColor.RED, TileNumber.ONE)
		// when
		val result = instance1.equals(instance2)
		// then
		assertThat(result).isTrue()
	}

}