package hwr.oop.adapters.`in`

import hwr.oop.ports.out.SaveGamePort
import hwr.oop.students.group4.rummikub.core.Game.Companion.createNewGame
import hwr.oop.students.group4.rummikub.core.GameId
import hwr.oop.students.group4.rummikub.core.PlayerId
import java.util.UUID

class NewGameUseCase(
    private val saveGamePort: SaveGamePort
) {
    fun startGame(command: Command) {
        val playerIds = command.playersIds.map {playerId -> PlayerId(playerId) }
        val gameId = gameIdBasedOn(command)
        val game = createNewGame(
            gameId = gameId,
            players = playerIds,)
    }

    private fun gameIdBasedOn(command: Command): GameId {
        val nullableGameId = command.gameId
        val gameId = if (nullableGameId != null) {
            val uuid = UUID.fromString(nullableGameId)
            GameId.from(uuid)
        } else {
            GameId.random()
        }
        return gameId
    }

    data class Command(
        val gameId: String? = null,
        val playersIds: List<String>
    )
}