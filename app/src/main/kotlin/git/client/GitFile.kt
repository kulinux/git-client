package git.client

import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.Inflater
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

    fun readFile(path: String): ByteArray =
        File("${basePath}/${path}").readBytes()

    fun uncompress(content: ByteArray): String {
        val outputStream = ByteArrayOutputStream()
        val inflater = Inflater()

        return outputStream.use {
            val buffer = ByteArray(1024)

            inflater.setInput(content)

            var count = -1
            while (count != 0) {
                count = inflater.inflate(buffer)
                outputStream.write(buffer, 0, count)
            }

            inflater.end()
            outputStream.toString("UTF-8")
        }
    }

}
