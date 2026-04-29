package hwr.oop.examples.template.core

class Tile(
    private val number: Int?,
    private val color: TileColor,
) {
	fun number(): Int {
		return number ?: 0
	}
	
	fun color(): TileColor {
		return color
	}
	
}
