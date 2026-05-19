package hwr.oop.examples.template.core

@JvmInline
value class PlayerId (private val id: String) {
    override fun toString(): String = id
}