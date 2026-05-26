package hwr.oop.examples.template.core.gamecommand

import hwr.oop.examples.template.core.Game
import hwr.oop.examples.template.core.GameState
import hwr.oop.examples.template.core.PlayerId
import hwr.oop.examples.template.core.Pool
import hwr.oop.examples.template.core.Tile
import hwr.oop.examples.template.core.TileColor
import hwr.oop.examples.template.core.TileNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class GameTests {

    companion object {
        private val player1 = PlayerId("player1")
        private val player2 = PlayerId("player2")
        private val player3 = PlayerId("player3")
        private val player4 = PlayerId("player4")

        private const val POOL_SIZE_WITHOUT_JOKER   = 104
        private const val TILES_PER_PLAYER          = 14

        @JvmStatic
        fun playerCombinations(): Stream<List<PlayerId>> = Stream.of(
            listOf(player1, player2),
            listOf(player1, player2, player3),
            listOf(player1, player2, player3, player4)
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 5])
    fun `invalid number of players, exception` (invalidInt: Int){
        //when
        val players: List<PlayerId> = (1..invalidInt).map { PlayerId("player$it") }
        //then
        Assertions.assertThatThrownBy {
            Game.createNewGame(
                players = players,
            )
        }.hasMessageContaining("has to have 2-4 players")
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `new Game without Joker, dealing of tiles correctly`(players: List<PlayerId>) {
        val game = Game.createNewGame(players = players)
        //when
        val racks = players.map { game.rackOf(it) }
        val poolSize = game.pool().tiles().size
        //then
        Assertions.assertThat(racks).hasSize(players.size).allMatch { it.tiles().size == TILES_PER_PLAYER }
        Assertions.assertThat(poolSize).isEqualTo(POOL_SIZE_WITHOUT_JOKER - (players.size * TILES_PER_PLAYER))
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `Order of players is correct`(players: List<PlayerId>) {
        //when
        val tiles = listOf(
            Tile(TileColor.BLUE, TileNumber.ONE),
            Tile(TileColor.BLUE, TileNumber.TWO),
            Tile(TileColor.BLUE, TileNumber.THREE),
            Tile(TileColor.BLUE, TileNumber.FOUR),
        )
        val playerMap = Game.determinePlayerOrder(players = players, Pool(tiles).toMutablePool())
        val playersAfterOrder = playerMap.keys.toList()
        //then
        Assertions.assertThat(playersAfterOrder).containsExactlyElementsOf(players.reversed())
    }

    @Test
    fun `Exception for players using same name` () {
        //given
        val trollPlayers = listOf(PlayerId("troll"), PlayerId("troll"))
        //when
        //then
        Assertions.assertThatThrownBy { Game.createNewGame(trollPlayers) }.isInstanceOf(IllegalArgumentException::class.java)
        Assertions.assertThatThrownBy { Game.createNewGame(trollPlayers) }.hasMessageContaining("unique names")

    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `Exception rackOf nonexisting player` (players: List<PlayerId>) {
        val game = Game.createNewGame(players = players)
        //when
        val troll = PlayerId("troll")
        //then
        assertThrows<NullPointerException> {game.rackOf(troll)}
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `game loaded successfully` (players: List<PlayerId>) {
        val game = Game.createNewGame(players = players)
        val gameState = GameState.fromGame(game)
        val loadedGame = Game.loadGame(gameState)
        //when
        //then
        Assertions.assertThat(loadedGame).isEqualTo(game)
    }

}