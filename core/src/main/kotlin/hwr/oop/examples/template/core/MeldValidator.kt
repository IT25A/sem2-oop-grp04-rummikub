package hwr.oop.examples.template.core

class MeldValidator (
    private val meld: Meld,
) {
    private fun points(): Int = meld.sets().sumOf { set -> set.points() }

    private fun isValidMeld(): Boolean = points() >= 30
}