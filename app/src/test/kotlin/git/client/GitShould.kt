package git.client

import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk
import io.mockk.verify

class GitShould : StringSpec({
    "Initialize git" {
        val gitInit = mockk<GitInit>(relaxed = true)
        val git = Git(gitInit)
        git.init()
        verify { gitInit.initialize() }
    }

    "Cat git" {
        val hash = "50831fc6410c1432d54f2809ed3c87e9525ca3"
        val gitInit = mockk<GitInit>(relaxed = true)
        val gitCat = mockk<GitCat>(relaxed = true)
        val git = Git(gitInit, gitCat)
        git.catFile(hash)
        verify { gitCat.catFile(hash) }
    }
})
