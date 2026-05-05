package hwr.oop.examples.template.core

class Tile (val color: TileColor, val number: TileNumber){
	
	fun color(): TileColor {
		return this.color
	}
	
	fun number(): TileNumber {
		return this.number
	}
	
}