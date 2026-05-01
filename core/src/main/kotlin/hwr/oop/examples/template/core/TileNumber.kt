package hwr.oop.examples.template.core

enum class TileNumber(
    private val value: Int
) {
    N1(1), N2(2), N3(3), N4(4),
    N5(5), N6(6), N7(7), N8(8),
    N9(9), N10(10), N11(11), N12(12), N13(13);

    fun value() = value
}