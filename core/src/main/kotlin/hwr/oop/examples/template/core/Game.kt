package hwr.oop.examples.template.core

import kotlin.collections.Set

class Game(
    private val racksOfPlayers: List<Rack>,
    private val players: List<PlayerId> = racksOfPlayers.map { it.owner() },
    private val openedPlayers: Set<PlayerId> =emptySet(),
    private val currentPlayerIndex: Int = 0,
    private val currentPlayer: PlayerId = players[currentPlayerIndex],
    private val board: Board = Board(),
    private val pool: Pool,

) {
    companion object {
        private const val INITIAL_TILES = 14

        fun createNewGame(players: List<PlayerId>): Game {
            require(players.size in 2..4) {"Rummikub has to have 2-4 players!"}
            require(players.distinct().size == players.size) { "Players must have unique names!" }
            val mutablePool: PoolMutable = Pool.createShuffledPool().toMutablePool()
            val racks = dealRacks(players, mutablePool)
            return Game(racksOfPlayers = racks, pool = mutablePool.toPool())
        }

        fun dealRacks(players: List<PlayerId>, pool: PoolMutable): List<Rack> {
            val playerTiles = players.associateWith { _ -> mutableListOf<Tile>() }
            repeat(INITIAL_TILES) {
                players.forEach { player ->
                    val rackTiles = playerTiles.getValue(player)
                    val drawnTile = pool.draw()
                    rackTiles.addAll(drawnTile)
                }
            }
            val racks = players.map { player ->
                val rackTiles = playerTiles.getValue(player)
                Rack(player, rackTiles)
            }
            return racks
        }
    }

    //Queries
    fun rackOf(player: PlayerId): Rack {
        require(player in players) { "Player $player is not in players" }
        return racksOfPlayers.find { it.owner() == player }!!
    }
    fun players(): List<PlayerId> = players
    fun pool(): Pool = pool
    fun board(): Board = board
    fun openedPlayers(): Set<PlayerId> = openedPlayers
    fun currentPlayer(): PlayerId = currentPlayer
    fun gameIsFinished(): Boolean = racksOfPlayers.any { it.tiles().isEmpty() }

    //Game Actions
    fun drawTile(player: PlayerId): Game {
        try {
            require(player == currentPlayer) { "It is not $player's turn - current player: $currentPlayer" }
            val rackTiles = rackOf(player).tiles()
            val drawnTile = pool.toMutablePool().draw()
            val rackTiles = rackTiles + drawnTile


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}