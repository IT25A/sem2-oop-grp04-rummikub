package hwr.oop.examples.template.core

import kotlinx.serialization.Serializable


@Serializable
data class GameState(
    private val gameId: String,
    private val status: GameStatus,
    private val currentPlayerId: PlayerId,
    private val winnerId: PlayerId?,
    private val table: Table,
    private val playerRacks: List<Rack>,
    private val pool: Pool,
) {
    companion object {
        fun fromGame(game: Game): GameState = GameState(
            gameId = game.id(),
            status = game.status(),
            currentPlayerId = game.currentPlayer(),
            winnerId = game.winner(),
            table = game.table(),
            playerRacks = game.racks(),
            pool = game.pool(),
        )
    }

    //Query
    fun gameId(): String = gameId
    fun status(): GameStatus = status
    fun currentPlayer(): PlayerId = currentPlayerId
    fun winner(): PlayerId? = winnerId
    fun table(): Table = table
    fun racks(): List<Rack> = playerRacks
    fun pool(): Pool = pool
}
