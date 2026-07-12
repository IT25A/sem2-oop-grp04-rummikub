package hwr.oop.adapters.`in`

import hwr.oop.ports.out.LoadGameByIdPort
import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.GameId

class LoadGameByIdQuery(
    private val loadGameByIdPort: LoadGameByIdPort
) {
    fun loadGameById(gameId: String): Game {
        val gameId = GameId(gameId)
        return loadGameByIdPort.loadById(gameId)
    }
}