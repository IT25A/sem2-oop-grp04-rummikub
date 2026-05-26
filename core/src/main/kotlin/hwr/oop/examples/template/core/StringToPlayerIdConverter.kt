package hwr.oop.examples.template.core

object StringToPlayerIdConverter {
    fun convert(vararg strings: String) = convert(strings.toList())

    private fun convert(list: List<String>): List<PlayerId> = list.map { it.toPlayerId() }

    fun String.toPlayerId(): PlayerId {
        require(isNotEmpty()) { "String must not be empty" }
        require(isNotBlank()) { "String must not be blank" }
        return PlayerId(this)
    }
}