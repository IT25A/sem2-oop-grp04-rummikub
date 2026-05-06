package hwr.oop.students.group4.rummikub.core

enum class TileNumber(
	private val value: Int
) {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	ELEVEN(11),
	TWELVE(12),
	THIRTEEN(13);
	
	fun value(): Int = value
}