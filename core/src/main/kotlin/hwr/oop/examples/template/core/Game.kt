package hwr.oop.examples.template.core

class Game (
    private val racksOfPlayers: List<Rack>,
    private val players: List<PlayerId> = racksOfPlayers.map { it.owner() },
) {
    companion object {
        fun createNewGame(players: List<PlayerId>, withJoker: Boolean): Game {
            require(players.size in 2..4) { "Rummikub has to have 2-4 Players" }
            val pool = Pool.createShuffledPool(withJoker).toMutablePool()
            val racks = dealRacksInitial(players, pool)
            return Game(racksOfPlayers = racks)
        }

        private fun dealRacksInitial(
            players: List<PlayerId>,
            pool: PoolMutable,
        ): List<Rack> {
            val playerRackTiles = players.associateWith { mutableListOf<Tile>() }
            repeat (14) {
                players.forEach { player ->
                    val rackTiles = playerRackTiles.getValue(player)
                    rackTiles.addAll(pool.draw())
                }
            }
            val racks = players.map { player ->
                val rackTiles = playerRackTiles.getValue(player)
                Rack(player, rackTiles)
            }
            return racks
        }
    }

    fun rackOf(player: PlayerId): Rack {
        require(player in players) { "Player $player is not in players" }
        return racksOfPlayers.find { it.owner() == player }!!
    }
}