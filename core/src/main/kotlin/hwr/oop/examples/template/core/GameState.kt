package hwr.oop.examples.template.core

import java.util.UUID

data class GameState(
    private val gameId: UUID,
    private val status: GameStatus,
    private val currentPlayerId: PlayerId,
    private val winnerId: PlayerId?,
    private val table: Table,
    private val playerRacks: List<Rack>,
    private val pool: Pool,
) {
    companion object {
        fun fromGame(gameId: UUID, game: Game): GameState = GameState(
            gameId = gameId,
            status = if (game.isFinished()) GameStatus.FINISHED else GameStatus.IN_PROGRESS,
            currentPlayerId = game.currentPlayer(),
            winnerId = game.winner(),
            table = game.table(),
            playerRacks = game.racks(),
            pool = game.pool(),
        )
    }

    //queries
    fun gameId(): UUID = gameId
    fun status(): GameStatus = status
    fun currentPlayer(): PlayerId = currentPlayerId
    fun table(): Table = table
    fun racks(): List<Rack> = playerRacks
    fun pool(): Pool = pool
}
