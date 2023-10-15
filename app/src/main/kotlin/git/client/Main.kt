package git.client

import git.client.MainValidatorArguments.validate

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            validate(args)
            val git = Git.apply(".")
            val command = args[0]!!
            val output = when (command) {
                "cat-file" -> git.catFile(args[2])
                "hash-object" -> git.hashObject(args[2])
                else -> throw RuntimeException("Command not found")
            }
            print(output)
        }
    }
}