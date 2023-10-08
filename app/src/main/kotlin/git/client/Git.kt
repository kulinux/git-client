package git.client

import java.nio.file.Paths

class Git internal constructor(
    private val gitInit: GitInit = defaultGitInit(),
    private val gitCat: GitCat = defaultGitCat()
) {
    fun init() {
        gitInit.initialize()
    }

    fun catFile(hash: String) {
        gitCat.catFile(hash)
    }

    companion object {
        fun apply(basePath: String) = Git(GitInit(GitFile(basePath)))
    }

}


private fun defaultGitInit() = GitInit(GitFile(Paths.get("").toAbsolutePath().toString()))
private fun defaultGitCat(): GitCat = GitCat(GitFile(Paths.get("").toAbsolutePath().toString()))
