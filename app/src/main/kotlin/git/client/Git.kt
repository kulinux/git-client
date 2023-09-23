package git.client

class Git internal constructor(private val gitInit: GitInit) {
    fun init() {
        gitInit.initialize()
    }
    companion object {
        fun apply(basePath: String) = Git(GitInit(GitFile(basePath)))
    }

}
