package hwr.oop.examples.template.core
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

//@ParameterizedTest
// Dieser Test wird mehrfach ausgeführt.
// Er läuft einmal für jede Zahl aus @ValueSource.
class RunsTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    //Der Wert einer Run ist die Summe aller Zahlen der Steine.
    //ab 12 meldet fehler da summe nicht reicht
    fun `run value equals sum of all tile numbers`(startNumber: Int) {
        for (color in Color.entries) {
            val maxLength = 13 - startNumber + 1
            //Hier wird berechnet, wie lang die Run maximal sein kann

            for (length in 3..maxLength) {
                val tiles = (0 until length).map { offset ->
                    Tile(color, startNumber + offset)
                }

                val run = Run(tiles)

                val expectedValue = tiles.sumOf { it.number }

                assertThat(run.value()).isEqualTo(expectedValue)
            }
        }
    }


    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    // maximale Länge: von startNumber bis 13 --> weil RUNS ab 3 möglich

    fun `run with any length works for any color and start number`(startNumber: Int) {
        for (color in Color.entries) { //ALLE FARBEN
            //  längste Run ab 10 ist: maxLength = 13 - 10 + 1
            //  maxLength = 4
            // 10, 11, 12, 13
            val maxLength = 13 - startNumber + 1

            for (length in 3..maxLength) {
                //ab 3 Steine
                // erstelle `length` aufeinanderfolgende Steine
                val tiles = (0 until length).map { offset ->
                    Tile(color, startNumber + offset)
                    //OFFSET heißt wie weit von startnummer entfernt
                    //offset = 0 -> Tile(color, 5 + 0) -> Tile(color, 5)
                    //offset = 1 -> Tile(color, 5 + 1) -> Tile(color, 6)
                    //offset = 2 -> Tile(color, 5 + 2) -> Tile(color, 7)
                //erstelle length Steine, die alle die gleiche Farbe haben, aber fortlaufend ab startNumber.
                }

                // when
                val run = Run(tiles)

                // then
                assertThat(run.tiles).hasSize(length)
                assertThat(run.tiles).allMatch { it.color == color }
                assertThat(run.tiles.first().number).isEqualTo(startNumber)
                assertThat(run.tiles.last().number).isEqualTo(startNumber + length - 1)
            }
        }
    }

    @ParameterizedTest // Allgemeiner Test für 3 steine
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `run can start at any number from 1 to 11`(startNumber: Int) {
        // Skip wenn startNumber > 11, weil dann tile3 (startNumber+2) > 13 wäre
        if (startNumber > 11) return
        for (color in Color.entries) {
            // given
            val tile1 = Tile(color, startNumber)
            val tile2 = Tile(color, startNumber + 1)
            val tile3 = Tile(color, startNumber + 2)
            val tiles = listOf(tile1, tile2, tile3)

            // when
            val run = Run(tiles)

            // then
            assertThat(run.tiles.first().number).isEqualTo(startNumber)
            //Erklärung : run.tiles: die Liste der Steine
            //run.tiles.first() Das nimmt den ersten Stein aus der Liste.
        // setzt dies als Startzahl dann +1 +2 in die schleife

        }


    }
    @ParameterizedTest //nur für Rot. dasselbe können wir für restliche Farben übernehmen
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `run can start at any number from 1 to 11 in ROT `(startNumber: Int) {
        // Skip wenn startNumber > 11, weil dann tile3 (startNumber+2) > 13 wäre
        if (startNumber > 11) return

        // given
        val tile1 = Tile(Color.RED, startNumber)
        val tile2 = Tile(Color.RED, startNumber + 1)
        val tile3 = Tile(Color.RED, startNumber + 2)
        val tiles = listOf(tile1, tile2, tile3)

        // when
        val run = Run(tiles)

        // then
        assertThat(run.tiles.first().number).isEqualTo(startNumber)
    } // Dieser Test prüft alle möglichen Start-Punkte für eine 3er-Run (1-13

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12])
    fun `run with only 2 tiles is invalid for any start number`(startNumber: Int) {
        for (color in Color.entries) //prüft in allen Farben
        {
            val tiles = listOf(
                Tile(color, startNumber),
                Tile(color, startNumber + 1),
            )

            assertThatThrownBy {
                Run(tiles)
            }.hasMessageContaining("at least 3")
        }
    }
    @ParameterizedTest //NEGATIVE Test
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    //10 + 11 +13 gab, bei 11 12 14 Falsch
    fun `run with gap in numbers is invalid`(startNumber: Int) {
        for (color in Color.entries) {
            val tiles = listOf(
                Tile(color, startNumber),
                Tile(color, startNumber + 1),
                Tile(color, startNumber + 3),
            )
            //Ein Test ist ungültig, wenn die zahlen nicht aufeinander folgen

            assertThatThrownBy {
                Run(tiles)
            }.hasMessageContaining("consecutive")
        }
    }
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    // Das sind die möglichen Startzahlen. bis 11? Weil wir immer 3 Steine bauen:
// startNumber, startNumber + 1, startNumber + 2
    fun `run with one different color is invalid`(startNumber: Int) {
        for (correctColor in Color.entries)
        // Diese Schleife geht alle Farben durch.correctColor ist die Farbe, die alle Steine haben sollten.
        {
            val wrongColor = Color.entries.first { it != correctColor }
            //Hier wird eine Farbe gesucht, die NICHT correctColor ist

            val tiles = listOf(
                Tile(correctColor, startNumber),
                Tile(correctColor, startNumber + 1),
                Tile(wrongColor, startNumber + 2),
            )//prüfe dür 3 steine

            assertThatThrownBy {
                Run(tiles)
            }.hasMessageContaining("same color")
        }
    }

    @Test //given HARTCODIERT ALS BSP
    fun `runs with minimum 3 numbers `(){
        val tileRed = Tile(Color.RED, 5)
        val tileRed2 = Tile(Color.RED, 6)
        val tileRed3 = Tile(Color.RED, 7)
        val tiles = listOf(tileRed, tileRed2, tileRed3)
        //when
        val run= Run(tiles)
        //then
        assertThat(run.tiles).hasSize(tiles.size)
        assertThat(run.tiles).allMatch { it.color == Color.RED }

    }
    @Test //Hartcodiert als BSP
    fun `runs with with different colors is invalid  `() {
        val tile1 = Tile(Color.RED, 5)
        val tile2 = Tile(Color.BLUE, 6)
        val tile3 = Tile(Color.RED, 7)
        val tiles1 = listOf(tile1, tile2, tile3)
        assertThatThrownBy { Run(tiles1) }.hasMessageContaining("not the same color")
    }
    @ParameterizedTest
    @EnumSource(Color::class) //@EnumSource(Color::class) "Ruf diese Funktion einmal pro Enum-Wert auf."
    fun `run with 3 consecutive tiles works in any color`(color: Color) {
        // given
        val tile1 = Tile(color, 5)
        val tile2 = Tile(color, 6)
        val tile3 = Tile(color, 7)
        val tiles = listOf(tile1, tile2, tile3)

        // when
        val run = Run(tiles)

        // then
        assertThat(run.tiles).hasSize(tiles.size)
        assertThat(run.tiles).allMatch { it.color == color }
    }//Macht der das nur für 5, 6, 7


}