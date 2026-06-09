package hwr.oop.examples.template.core

interface GameRepository {
    fun load(gameId: String?): GameState
    fun save(gameState: GameState)
}