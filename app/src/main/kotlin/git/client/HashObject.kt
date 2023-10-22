package git.client

import java.security.MessageDigest

class HashObject(private val gitFile: GitFile) {
    fun hashObject(file: String) {
        val content = gitFile.readFile(file)
        val compressedContent = gitFile.compress(content.decodeToString())
        val hash = hash(content.decodeToString())
        gitFile.writeFile(".git/objects/$hash", compressedContent)
    }

    private fun hash(content: String): String {
        return shasum("blob 12" + '\u0000' + content)
    }

    private fun shasum(content: String): String {
        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        val encodedhash: ByteArray = digest.digest(content.toByteArray())
        return encodedhash.fold("", { str, it -> str + "%02x".format(it) })
    }

}
