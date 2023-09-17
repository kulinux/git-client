package git.client

class GitInit constructor(private val file: GitFile) {
    fun initialize() {
        file.mkdir(".git")
    }

}
