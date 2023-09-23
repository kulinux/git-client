package git

import git.client.Git
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.file.shouldBeADirectory
import io.kotest.matchers.file.shouldBeAFile
import io.kotest.matchers.shouldBe
import java.io.File

const val BasePath = "target/example"

fun String.asFile() = File(this)

class GitSpec : FeatureSpec({

    "${BasePath}/.git".asFile().deleteRecursively()
    val git = Git.apply(BasePath)
    git.init()

    feature("git init should give a ") {
        scenario("correct .git directory") {
            "${BasePath}/.git".asFile().shouldBeADirectory()
            "${BasePath}/.git/objects".asFile().shouldBeADirectory()
            "${BasePath}/.git/refs".asFile().shouldBeADirectory()
            "${BasePath}/.git/HEAD".asFile().shouldBeAFile()
            "${BasePath}/.git/HEAD".asFile().readText().shouldBe("ref: refs/heads/master\n")
        }
    }

})