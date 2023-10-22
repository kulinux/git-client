package git.client

import io.kotest.core.spec.style.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class HashObjectShould : StringSpec({
    val file = "content.txt"
    val fileContent = "file content"
    val compressedFileContent = "compressed file content".toByteArray()
    val hash = "dd/59d098638313f5d00a7fa657379b33b191f2e2"
    lateinit var gitFile: GitFile
    lateinit var hashObject: HashObject

    beforeEach {
        gitFile = mockk(relaxed = true)
        hashObject = HashObject(gitFile)
        every { gitFile.readFile(file) } returns fileContent.toByteArray()
        every { gitFile.compress(fileContent) } returns compressedFileContent
    }

    "read file content" {
        hashObject.hashObject(file)
        verify { gitFile.readFile(file) }
    }

    "compress content" {
        hashObject.hashObject(file)
        verify { gitFile.compress(fileContent) }
    }

    "write on hash path" {
        hashObject.hashObject(file)
        verify { gitFile.writeFile(".git/objects/$hash", compressedFileContent) }
    }
})