package hwr.oop.adapters.`in`

import hwr.oop.ports.out.LoadGameByIdPort
import hwr.oop.ports.out.SaveGamePort
import hwr.oop.students.group4.rummikub.core.GameId

//class PlayTileUseCase(
//    private val loadGameByIdPort: LoadGameByIdPort,
//    private val saveGamePort: SaveGamePort
//) {
//    fun playAction(command: Command) {
//        val gameId = GameId(command.gameId)
//        val newTable = command.table
//        val loadedGame = loadGameByIdPort.loadById(gameId)
//        val updatedGame = loadedGame.playTiles()
//    }
//
//    data class Command (
//        val gameId: String,
//        val player: String,
//        val table: String
//    )
//}