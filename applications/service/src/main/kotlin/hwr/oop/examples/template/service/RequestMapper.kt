package hwr.oop.examples.template.service

import hwr.oop.adapters.`in`.NewGameUseCase
import hwr.oop.examples.template.service.model.StartGameRequest
import hwr.oop.examples.template.service.model.PlayTilesRequest
import hwr.oop.examples.template.service.model.DrawTileRequest
import hwr.oop.students.group4.rummikub.core.GameId
import hwr.oop.students.group4.rummikub.core.PlayerId
import java.util.UUID

//object RequestMapper {
//    fun StartGameRequest.asCommand() = NewGameUseCase.Command(
//        gameId = UUID.randomUUID().toString(),
//        playersIds = this.playerIds,
//    )
//
//    fun PlayTilesRequest.asCommand(gameId: String) = PlayTileUseCase.Command(
//        gameId = gameId,
//        player = this.playerId,
//        table = this.table.toString()
//    )
//
//    fun DrawTileRequest.asCommand(gameId: String) = DrawTileUseCase.Command()
//}