package git.client

class GitInit(private val file: GitFile) {
    fun initialize() {
        listOf(
            ".git",
            ".git/objects",
            ".git/refs"
        ).forEach { file.mkdir(it) }

        file.writeFile(".git/HEAD", "ref: refs/heads/master\n")
    }

}
