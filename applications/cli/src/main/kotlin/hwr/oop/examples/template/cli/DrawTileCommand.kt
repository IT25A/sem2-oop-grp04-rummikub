package hwr.oop.examples.template.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required

class DrawTileCommand : CliktCommand(name = "drawTile") {
	private val gameId by requireObject<String>()
	private val playerId by option("--player-id", help = "The ID of the player drawing a tile.").required()
	
	override fun run(): Unit {
	}
}
