package hwr.oop.students.group4.rummikub.core

import kotlinx.serialization.Serializable

@Serializable
data class Tile (val color: TileColor, val number: TileNumber) {

	fun color(): TileColor = color
	
	fun number(): TileNumber = number
}