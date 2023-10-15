package git.client

import io.kotest.core.spec.style.StringSpec
import io.mockk.*

class MainShould : StringSpec({

    val hash = "object hash"
    val file = "content.txt"
    val catFileArgs = arrayOf("cat-file", "-p", hash)
    val hashObjectArgs = arrayOf("hash-object", "--path", file)
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

    "call catFile" {
        Main.main(catFileArgs)
        verify { git.catFile(hash) }
    }

    "call hashFile" {
        Main.main(hashObjectArgs)
        verify { git.hashObject(file) }
    }

    "call validator" {
        Main.main(catFileArgs)
        verify { MainValidatorArguments.validate(catFileArgs) }
    }

})