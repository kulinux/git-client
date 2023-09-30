package git.client

import io.kotest.core.spec.style.StringSpec
import io.mockk.*

class MainShould : StringSpec({

    val hash = "object hash"
    val catFileArgs = arrayOf("cat-file", "-p", hash)
    lateinit var git: Git

    beforeEach {
        git = mockk(relaxed = true)
        mockkObject(MainValidatorArguments)
        every { MainValidatorArguments.validate(any()) } returns Unit
    }

    afterEach {
        unmockkObject(MainValidatorArguments)
    }

    "call catFile with the hash" {
        Main.main(catFileArgs, git)
        verify { git.catFile(hash) }
    }

    "call validator" {
        Main.main(catFileArgs, git)
        verify { MainValidatorArguments.validate(catFileArgs) }
    }

    /*
    "call raise an error on unknown command" {
        val exception = shouldThrow<RuntimeException> {
            Main.main(arrayOf("unknown command"), git)
        }
        exception.message shouldBe "Unknown command"
    }

    "call raise an error on no command" {
        val exception = shouldThrow<RuntimeException> {
            Main.main(emptyArray(), git)
        }
        exception.message shouldBe "Command not found"
    }
     */
})