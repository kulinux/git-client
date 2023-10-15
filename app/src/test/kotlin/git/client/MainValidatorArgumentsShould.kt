package git.client

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MainValidatorArgumentsShould : FreeSpec({
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

    "cat-file" - {

        "should raise an exception on wrong argument" {
            val exception = shouldThrow<RuntimeException> {
                MainValidatorArguments.validate(arrayOf("cat-file", "-u", "mono"))
            }
            exception.message shouldBe "Wrong arguments for cat-file"
        }

        "should raise an exception on incorrect number of argument" {
            val exception = shouldThrow<RuntimeException> {
                MainValidatorArguments.validate(arrayOf("cat-file", "-p"))
            }
            exception.message shouldBe "Wrong arguments for cat-file"
        }

        "should not throw error on correct arguments" {
            shouldNotThrowAny {
                MainValidatorArguments.validate(arrayOf("cat-file", "-p", "mono"))
            }
        }
    }

    "hash-object" - {
        "should raise an exception on wrong argument" {
            val exception = shouldThrow<RuntimeException> {
                MainValidatorArguments.validate(arrayOf("hash-object", "-u", "mono"))
            }
            exception.message shouldBe "Wrong arguments for hash-object"
        }

        "should raise an exception on incorrect number of argument" {
            val exception = shouldThrow<RuntimeException> {
                MainValidatorArguments.validate(arrayOf("hash-object", "-p"))
            }
            exception.message shouldBe "Wrong arguments for hash-object"
        }

        "should not throw error on correct arguments" {
            shouldNotThrowAny {
                MainValidatorArguments.validate(arrayOf("hash-object", "--path", "mono"))
            }
        }


    }
})