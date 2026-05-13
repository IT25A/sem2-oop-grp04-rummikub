package hwr.oop.examples.template.core

data class Meld (
    private val sets: MutableList<Set>,
    private val owner: PlayerId,
) {
    fun sets() = sets
}