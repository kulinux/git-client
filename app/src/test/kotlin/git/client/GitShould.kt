package git.client

import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk
import io.mockk.verify

class GitShould : StringSpec({
    val gitInit = mockk<GitInit>(relaxed = true)
    val catFile = mockk<CatFile>(relaxed = true)
    val hashObject = mockk<HashObject>(relaxed = true)
    val git = Git(gitInit, catFile, hashObject)

    "Initialize git" {
        git.init()
        verify { gitInit.initialize() }
    }

    "cat file" {
        val hash = "50831fc6410c1432d54f2809ed3c87e9525ca3"
        git.catFile(hash)
        verify { catFile.catFile(hash) }
    }

    "hash object" {
        val file = "content.txt"
        git.hashObject(file)
        verify { hashObject.hashObject(file) }
    }
})
