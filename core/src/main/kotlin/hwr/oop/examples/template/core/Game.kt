package hwr.oop.examples.template.core

import java.util.UUID

data class Game(
    private val gameId: String,
    private val gameStatus: GameStatus,
    private val playerRacks: List<Rack>,
    private val players: List<PlayerId> = playerRacks.map { it.owner() },
    private val winner: PlayerId? = null,
    private val currentPlayer: PlayerId,
    private val table: Table,
    private val pool: Pool,
    ) {
    companion object {
        private const val INITIAL_TILES = 14

        fun createNewGame(players: List<PlayerId>): Game {
            require(players.size in 2..4) {"Rummikub has to have 2-4 players!"}
            require(players.distinct().size == players.size) { "Players must have unique names!" }
            val mutablePool: PoolMutable = Pool.createShuffledPool().toMutablePool()
            val racks = dealRacks(players, mutablePool)
            val uuid = UUID.randomUUID().toString()
            return Game(
                gameId= uuid,
                gameStatus = GameStatus.IN_PROGRESS,
                playerRacks =  racks,
                pool = mutablePool.toPool(),
                currentPlayer = racks.first().owner(),
                table = Table(),
            )
        }

        fun dealRacks(players: List<PlayerId>, pool: PoolMutable): List<Rack> {
            val playerTiles = determinePlayerOrder(players, pool)
            repeat(INITIAL_TILES - 1) {
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

        fun determinePlayerOrder(players: List<PlayerId>, pool: PoolMutable): Map<PlayerId, MutableList<Tile>> {
            return players.associateWith { pool.draw().toMutableList() }
                .entries
                .sortedByDescending { (_, tiles) -> tiles.first().number().points() }
                .associate { it.key to it.value }
        }

        fun loadGame(gameState: GameState): Game {
            return Game(
                gameId = gameState.gameId(),
                playerRacks = gameState.racks(),
                gameStatus = gameState.status(),
                pool = gameState.pool(),
                currentPlayer = gameState.currentPlayer(),
                table = gameState.table(),
            )
        }
    }
    //Commands
    fun drawTile(player: PlayerId): Game {
        verifyTurn(player)
        require(pool.tiles().isNotEmpty()) { "Pool $pool is empty!" }

        val mutablePool = pool.toMutablePool()
        val drawnTile: List<Tile> = mutablePool.draw()
        val updatedRacks = playerRacks.map { rack ->
            if (rack.owner() == player) {
                rack.addTiles(drawnTile)
            } else {
                rack
            }
        }

        return copy(
            playerRacks = updatedRacks,
            currentPlayer = nextPlayer(),
            pool= mutablePool.toPool(),
        )
    }
    fun playTiles(newTable: Table, player: PlayerId): Game {
        verifyTurn(player)
        require(newTable.isNotEmpty()) { "New Table cannot be empty when playing tiles!" }


        val newSets: List<Set> = newTable.sets()
        newSets.forEach { set -> set.type() }
        val oldSets: List<Set> = table.sets()

        val newTableTiles: List<Tile> = newSets.flatMap { set -> set.tiles() }
        val oldTableTiles: List<Tile> = oldSets.flatMap { set -> set.tiles() }
        val addedTableTiles: List<Tile> = newTableTiles.toMutableList().apply {
            oldTableTiles.forEach { remove(it) }
        }

        val points: Int = newTable.totalPoints()
        val currentRack = rackOf(player)
        addedTableTiles.forEach { tile -> require(currentRack.hasTile(tile)) { "Tile is not in ${player.id()}'s rack" } }

        if (!currentRack.isOpen()) {
            require(points >= 30) { "Initial meld requires having 30 or more points" }
        }

        val updatedRacks = playerRacks.map { rack ->
            if (rack.owner() == player) {
                rack.removeTiles(addedTableTiles)
            } else {
                rack
            }
        }

        return if (updatedRacks.any{ it.tiles().isEmpty() }) {
             copy(
                 gameId = this.gameId,
                 playerRacks = updatedRacks,
                 table = newTable,
                 gameStatus = GameStatus.FINISHED,
                 winner = winner(),
            )
        } else {
             copy(
                playerRacks = updatedRacks,
                currentPlayer = nextPlayer(),
                table = newTable,
            )
        }
    }

    fun verifyTurn(player: PlayerId) {
        require(this.status() != GameStatus.FINISHED) { "Game is finished!" }
        require(player in players) { "Player ${player.id()} is not in players" }
        require(player == currentPlayer) { "Player ${player.id()} is not current player, $currentPlayer is." }
    }

    //Queries
    fun id(): String = gameId
    fun rackOf(player: PlayerId): Rack = playerRacks.find { it.owner() == player }!!
    fun winner(): PlayerId? = playerRacks.find { it.tiles().isEmpty() }?.owner()
    fun currentPlayer(): PlayerId = currentPlayer
    fun nextPlayer(): PlayerId = players[ (players.indexOf(currentPlayer) + 1) % players.size ]
    fun pool(): Pool = pool
    fun racks(): List<Rack> = playerRacks
    fun table(): Table = table
    fun isFinished(): Boolean = playerRacks.any { it.tiles().isEmpty() }
    fun status(): GameStatus = gameStatus
}