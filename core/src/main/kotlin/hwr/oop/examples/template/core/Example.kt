package hwr.oop.examples.template.core

class Example {
	fun sayHelloTo(string: String) = "Hello $string!"
}

fun main() {
	val tile = Tile(TileColor.YELLOW, 12)
	println("color=${tile.color()}, number=${tile.number()}")
}
