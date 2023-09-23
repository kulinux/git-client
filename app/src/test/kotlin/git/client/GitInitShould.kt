package git.client

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.mockk.mockk
import io.mockk.verify

class GitInitShould : FunSpec({
    val file = mockk<GitFile>(relaxed = true)
    val gitInit = GitInit(file)
    gitInit.initialize()

    context("initialize directories") {
        withData(".git", ".git/objects", ".git/refs") {
            verify { file.mkdir(it) }
        }
    }

    context("create ref") {
        verify { file.writeFile(".git/HEAD", "ref: refs/heads/master\n") }
    }
})
