package git

import git.client.Git
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.file.shouldBeADirectory
import io.kotest.matchers.file.shouldBeAFile
import io.kotest.matchers.shouldBe
import java.io.File
import java.lang.RuntimeException

const val BasePath = "target/example"

fun String.asFile() = File(this)
fun String.execute(basePath: String = BasePath): String {
    val process = Runtime.getRuntime().exec(this, null, basePath.asFile())
    val status = process.waitFor()
    if(status != 0) {
        throw RuntimeException("Fail $this with $status: ${process.errorReader().readText()}")
    }
    return process.inputReader().readText()
}

class GitSpec : FeatureSpec({


    beforeTest {
        BasePath.asFile().deleteRecursively()
        val git = Git.apply(BasePath)
        git.init()
    }

    feature("git init should give a ") {
        scenario("correct .git directory") {
            "${BasePath}/.git".asFile().shouldBeADirectory()
            "${BasePath}/.git/objects".asFile().shouldBeADirectory()
            "${BasePath}/.git/refs".asFile().shouldBeADirectory()
            "${BasePath}/.git/HEAD".asFile().shouldBeAFile()
            "${BasePath}/.git/HEAD".asFile().readText().shouldBe("ref: refs/heads/master\n")
        }
    }

    feature("git object") {
        scenario("read blob") {
            val content = "test_content"
            val addFileCommand = "git hash-object -w content.txt"

            File("$BasePath/content.txt").writeText(content)
            val hash = addFileCommand.execute()
            val command = "../../your_git.sh cat-file -p $hash"


            command.execute().shouldBe(content)
        }

        scenario("create blob") {
            val content = "test_content"
            File("$BasePath/content.txt").writeText(content)
            val command = "../../your_git.sh hash-object --path content.txt"

            val hash = command.execute()

            val readFileCommand = "git cat-file -p $hash"

            readFileCommand.execute().shouldBe(content)
        }
    }

})