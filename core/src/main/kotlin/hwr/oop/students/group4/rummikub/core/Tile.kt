package hwr.oop.students.group4.rummikub.core

data class Tile (val color: TileColor, val number: TileNumber) {

	fun color(): TileColor = color
	
	fun number(): TileNumber = number
}