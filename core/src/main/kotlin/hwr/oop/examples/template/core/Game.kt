package hwr.oop.examples.template.core

class Game (
    private val racksOfPlayers: List<Rack>,
    private val players: List<PlayerId> = racksOfPlayers.map { it.owner() },
    private val pool: PoolMutable,
    private val board: Board = Board().toMutableBoard(),
    private val meldedPlayers: MutableSet<PlayerId> = mutableSetOf(),
    private val currentPlayerIndex: Int = 0,
) {
    companion object {
        private const val INITIAL_TILES = 14
        private const val MELD_THRESHOLD = 30

        fun createNewGame(players: List<PlayerId>, withJoker: Boolean): Game {
            require(players.size in 2..4) { "Rummikub has to have 2-4 Players" }
            val pool = Pool.createShuffledPool(withJoker).toMutablePool()
            val racks = dealRacksInitial(players, pool)
            return Game(racksOfPlayers = racks, pool = pool)
        }

        private fun dealRacksInitial(
            players: List<PlayerId>,
            pool: PoolMutable,
        ): List<Rack> {
            val playerRackTiles = players.associateWith { mutableListOf<Tile>() }
            repeat (INITIAL_TILES) {
                players.forEach { player ->
                    val rackTiles = playerRackTiles.getValue(player)
                    rackTiles.addAll(pool.draw())
                }
            }
            return players.map { player ->
                val rackTiles = playerRackTiles.getValue(player)
                Rack(player, rackTiles)
            }
        }
    }
// Queries
    fun rackOf(player: PlayerId): Rack {
        require(player in players) { "Player $player is not in players" }
        return racksOfPlayers.find { it.owner() == player }!!
    }

    fun currentPlayer(): PlayerId = players[currentPlayerIndex]

    fun pool(): PoolMutable = pool

    fun board(): Board = board

    fun hasPlayerMelded(player: PlayerId): Boolean = player in meldedPlayers

    fun isFinished(): Boolean = racksOfPlayers.any { it.tiles().isNotEmpty() }

    fun winner(): PlayerId? = racksOfPlayers.firstOrNull { it.tiles().isEmpty() }?.owner()

}