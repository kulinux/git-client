package git.client

import git.client.MainValidatorArguments.validate
import java.lang.RuntimeException

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            validate(args)
            val git = Git.apply(".")
            git.catFile(args[2])
        }
    }
}