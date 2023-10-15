package git.client


class CatFile(private val gitFile: GitFile) {
    fun catFile(hash: String): String {
        val dir = hash.subSequence(0, 2)
        val file = hash.substring(2)
        val compressed = gitFile.readFile(".git/objects/$dir/$file")
        val res = gitFile.uncompress(compressed)
        return res.replace("blob 12" , "").substring(1)
    }
}
