package git.client

import java.nio.file.Paths

class Git internal constructor(
    private val gitInit: GitInit = defaultGitInit(),
    private val catFile: CatFile = defaultGitCat(),
    private val hashObject: HashObject = defaultGitHash()
) {
    fun init() {
        gitInit.initialize()
    }

    fun catFile(hash: String): String =
        catFile.catFile(hash)

    fun hashObject(file: String) {
        hashObject.hashObject(file)
    }

    companion object {
        fun apply(basePath: String) = Git(GitInit(GitFile(basePath)))
    }

}


private fun defaultGitInit() = GitInit(GitFile(Paths.get("").toAbsolutePath().toString()))
private fun defaultGitCat(): CatFile = CatFile(GitFile(Paths.get("").toAbsolutePath().toString()))

private fun defaultGitHash(): HashObject = HashObject(GitFile(Paths.get("").toAbsolutePath().toString()))
