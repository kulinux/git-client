package git.client

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MainValidatorArgumentsShould : StringSpec({
    "call raise an error on unknown command" {
        val exception = shouldThrow<RuntimeException> {
            MainValidatorArguments.validate(arrayOf("unknown command"))
        }
        exception.message shouldBe "Unknown command"
    }

    "call raise an error on no command" {
        val exception = shouldThrow<RuntimeException> {
            MainValidatorArguments.validate(emptyArray())
        }
        exception.message shouldBe "Command not found"
    }
})