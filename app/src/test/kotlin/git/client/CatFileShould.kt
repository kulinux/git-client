package git.client

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class CatFileShould : FreeSpec({
    val file = mockk<GitFile>(relaxed = true)
    val catFile = CatFile(file)
    val compressedContent = "compressed content".toByteArray()
    val content = "content"
    beforeEach {
        every { file.readFile(any()) } returns compressedContent
        every { file.uncompress(any()) } returns ("blob 12" + '\u0000' + content)
    }

    "read the file of the hash considering the directory layout" {
        catFile.catFile("abcdefg")
        verify { file.readFile(".git/objects/ab/cdefg") }
    }

    "decompress content" {
        val res = catFile.catFile("abcdefg")
        verify { file.uncompress(compressedContent) }
        res shouldBe content
    }

})
