package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExampleTest {
	
	@Test
	fun `example hello world`() {
		// given
		val example = Example()
		// when
		val result = example.sayHelloTo("World")
		// then
		assertThat(result).startsWith("Hello").endsWith("!").contains("World")
	}
}