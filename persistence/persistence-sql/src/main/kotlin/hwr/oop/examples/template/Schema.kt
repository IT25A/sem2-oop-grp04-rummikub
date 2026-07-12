package hwr.oop.examples.template

import hwr.oop.students.group4.rummikub.core.Game
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.json.jsonb

private val format = Json {
    prettyPrint = false
    isLenient = true
    ignoreUnknownKeys = true
}

object RummikubGamesTable : UUIDTable("rummikub_games") {
    val game = jsonb<Game>("game", format)
}