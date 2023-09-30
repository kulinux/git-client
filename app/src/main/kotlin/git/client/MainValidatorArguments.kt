package git.client

import java.lang.RuntimeException

object MainValidatorArguments {
    fun validate(args: Array<String>) {
        if (args.isEmpty()) throw RuntimeException("Command not found")
        if (args[0] != "cat-file") throw RuntimeException("Unknown command")
        TODO("Not yet implemented")
    }

}
