/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package git.client

import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk
import io.mockk.verify

class GitInitShould: StringSpec( {
    "Create .git directory" {
        val file = mockk<GitFile>()
        val gitInit = GitInit()
        gitInit.initialize()
        verify { file.mkdir(".git") }
    }
})