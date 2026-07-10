package hwr.oop.ports.out

import hwr.oop.students.group4.rummikub.core.Game

interface SaveGamePort {
    fun save(game: Game): Unit
}