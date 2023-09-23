package git.client

import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk
import io.mockk.verify

class GitTest : StringSpec({
    "Initialize git" {
        val gitInit = mockk<GitInit>(relaxed = true)
        val git = Git(gitInit)
        git.init()
        verify { gitInit.initialize() }
    }
})
