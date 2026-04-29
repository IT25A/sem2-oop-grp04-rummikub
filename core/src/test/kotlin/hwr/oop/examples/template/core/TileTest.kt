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
	
	@Test
	fun `Tile_toString() returns number and color ` (){
		// given
		val number:Int = 7
		val color:TileColor = TileColor.RED
		// when
		val tile = Tile(number = number, color = color)
		// then
		assertThat(tile.toString()).isEqualTo("Tile: number=$number, color=$color")
	}
}