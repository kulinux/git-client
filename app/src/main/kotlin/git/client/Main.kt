package git.client

import git.client.MainValidatorArguments.validate
import java.lang.RuntimeException

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>, git: Git = Git()) {
            validate(args)
            git.catFile(args[2])
        }

        private fun validateArgs(args: Array<String>) {
        }
    }
}