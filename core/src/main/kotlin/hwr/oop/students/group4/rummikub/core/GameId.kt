package hwr.oop.students.group4.rummikub.core

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@JvmInline
value class GameId(val value: String) {
    companion object {
        fun random() = GameId(UUID.randomUUID().toString())
        fun from(uuid: UUID) = GameId(uuid.toString())
    }

    fun uuid(): UUID = UUID.fromString(value)
}