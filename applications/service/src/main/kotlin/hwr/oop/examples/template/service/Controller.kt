package hwr.oop.examples.template.service

import hwr.oop.examples.template.core.GameRepository
import hwr.oop.examples.template.service.api.GameActionApi
import hwr.oop.examples.template.service.api.GameApi
import hwr.oop.examples.template.service.model.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestController
class Controller(
	private val persistence: GameRepository,
) : GameApi, GameActionApi {
	override fun getGame(gameId: String?): ResponseEntity<GameState> {
		try {
			require(gameId != null) { "Game Id must not be null" }
			require(gameId.isNotEmpty()) { "Game Id must not be empty" }
			require(gameId.isNotBlank()) { "Game Id must not be blank" }



		} catch (ex: Exception) {
			//ExceptionHandler
		}
	}
	
	override fun startGame(startGameRequest: @Valid StartGameRequest?): ResponseEntity<GameCreatedResponse> {
		TODO("Not yet implemented")
	}
	
	override fun drawTile(
		gameId: String?,
		drawTileRequest: @Valid DrawTileRequest?,
	): ResponseEntity<GameState>? {
		TODO("Not yet implemented")
	}
	
	override fun playTiles(
		gameId: String?,
		playTilesRequest: @Valid PlayTilesRequest?,
	): ResponseEntity<GameState> {
		TODO("Not yet implemented")
	}
	
}
