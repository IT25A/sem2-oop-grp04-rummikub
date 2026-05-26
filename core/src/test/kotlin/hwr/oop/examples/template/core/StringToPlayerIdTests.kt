package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class StringToPlayerIdTests {
    private val converter = StringToPlayerIdConverter

    @ParameterizedTest
    @CsvSource(
        "ricardo, ricardo",
        "boas, boas",
        "anton, anton",
        "elissar, elissar",
        "melvin, melvin",
    )
    fun`string correctly parsed to playerId`(input:String, output: PlayerId){
        // when
        val playerId = with(converter) {
            input.toPlayerId()
        }
        //then
        assertThat(playerId).isEqualTo(output)
    }

    @Test
    fun `string blank, exception thrown`() {
        //when
        val blankString = "   "
        //then
        assertThatThrownBy {converter.convert(blankString)}.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {converter.convert(blankString)}.hasMessageContaining("String must not be blank")
    }

    @Test
    fun `string empty, exception`() {
        //when
        val emptyString = ""
        //then
        assertThatThrownBy {converter.convert(emptyString)}.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {converter.convert(emptyString)}.hasMessageContaining("String must not be empty")
    }
}