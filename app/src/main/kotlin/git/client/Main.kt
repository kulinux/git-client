package git.client

import git.client.MainValidatorArguments.validate
import java.lang.RuntimeException

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            validate(args)
            val git = Git.apply(".")
            val output = git.catFile(args[2])
            print(output)
        }
    }
}