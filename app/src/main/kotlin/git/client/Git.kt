package git.client

import java.nio.file.Paths

class Git internal constructor(private val gitInit: GitInit = defaultGit()) {
    fun init() {
        gitInit.initialize()
    }

    fun catFile(hash: String) {
        TODO("Not yet implemented")
    }

    companion object {
        fun apply(basePath: String) = Git(GitInit(GitFile(basePath)))
    }

}

private fun defaultGit() = GitInit(GitFile(Paths.get("").toAbsolutePath().toString()))
