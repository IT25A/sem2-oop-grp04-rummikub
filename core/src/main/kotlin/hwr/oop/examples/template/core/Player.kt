package hwr.oop.examples.template.core

class Player(val name: String) {
    private var opened: Boolean = false

    fun hasOpened(): Boolean = opened

    fun open(sets: List<Group>) {
        val totalPoints = sets.sumOf { it.value() }
        require(totalPoints >= 30) {
            "Initial move requires at least 30 points, got $totalPoints"
        }
        opened = true
    }
}