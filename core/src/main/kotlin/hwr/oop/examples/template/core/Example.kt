package hwr.oop.examples.template.core

class Example {
	fun sayHelloTo(string: String) = "Hello $string!"
}

fun main() {
	val tile = Tile(TileColor.YELLOW, TileNumber.N12)
	println("color=${tile.color()}, number=${tile.number().value()}")
}
