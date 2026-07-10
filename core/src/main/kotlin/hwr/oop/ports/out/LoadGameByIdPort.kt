package hwr.oop.ports.out

import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.GameId

interface LoadGameByIdPort {

    fun loadById(gameId: GameId): Game

    class CouldNotLoadException(
        gameId: GameId,
        cause: Exception? = null
    ) : RuntimeException(
        "Could not load game with id: $gameId",
        cause
    )
}