package hwr.oop.examples.template


import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.PlayerId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InMemoryPersistenceTest {

    @Test
    fun `save game`() {
        //given
        val gameObject = Game.createNewGame(players = listOf(PlayerId("Pooky"), PlayerId("Pooky2")))
        val persistence = InMemoryPersistence.createEmptyStore()
        //when
        persistence.save(gameObject)
        //then
        assertThat(gameObject).isEqualTo(persistence.loadById(gameObject.id()))
    }

    @Test
    fun `load game`() {
        // given
        val gameObject = Game.createNewGame(players = listOf(PlayerId("Pooky"), PlayerId("Pooky2")))
        val persistence = InMemoryPersistence.createWithGames(listOf(gameObject))
        // when
        val loadedGame = persistence.loadById(gameObject.id())
        // then
        assertThat(loadedGame).isEqualTo(gameObject)
    }
    @Test
    fun `load game with empty game`() {}

}