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
        mockkObject(Git)
        every { MainValidatorArguments.validate(any()) } returns Unit
        every { Git.apply(any()) } returns git
    }

    afterEach {
        unmockkObject(MainValidatorArguments)
        unmockkObject(Git)
    }

    "call catFile with the hash" {
        Main.main(catFileArgs)
        verify { git.catFile(hash) }
    }

    "call validator" {
        Main.main(catFileArgs)
        verify { MainValidatorArguments.validate(catFileArgs) }
    }

})