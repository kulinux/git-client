package git.client

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.createParentDirectories

class GitFile(private val basePath: String) {
    fun mkdir(dir: String) {
        val path = Path.of("${basePath}/${dir}")
        path.createParentDirectories()
        path.createDirectory()
    }

    fun writeFile(path: String, content: String) {
        File("${basePath}/${path}").writeText(content)
    }

}
