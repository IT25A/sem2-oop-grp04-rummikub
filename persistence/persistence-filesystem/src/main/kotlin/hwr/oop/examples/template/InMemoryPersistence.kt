package hwr.oop.examples.template

import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.GameId
import hwr.oop.ports.out.GameRepository
import hwr.oop.ports.out.LoadGameByIdPort
import java.util.UUID
import kotlin.collections.set

class InMemoryPersistence(
    private val games : MutableMap<UUID, Game>,
) : GameRepository {

    companion object {
        fun createEmptyStore(): InMemoryPersistence {
            val games = emptyMap<UUID, Game>().toMutableMap()
            return InMemoryPersistence(games)
        }

        fun createWithGames(listOfGames : List<Game>): InMemoryPersistence {
            val games = emptyMap<UUID, Game>().toMutableMap()
            listOfGames.forEach { games[it.id().uuid()] = it }
            return InMemoryPersistence(games)
        }

    }


    override fun save(game: Game) {
        games[game.id().uuid()] = game
    }

    override fun loadById(gameId: GameId): Game {
        return games[gameId.uuid()] ?: throw LoadGameByIdPort.CouldNotLoadException(gameId)
    }
}