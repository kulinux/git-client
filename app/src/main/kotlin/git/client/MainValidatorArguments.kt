package git.client

object MainValidatorArguments {

    private val ThreeArgumentsPattern = mapOf(
        "cat-file" to "-p",
        "hash-object" to "--path"
    )

    fun validate(args: Array<String>) {
        if (args.isEmpty()) throw RuntimeException("Command not found")
        val command = args[0]
        val expectedArgument = ThreeArgumentsPattern[command]
        when {
            args.size < 3 -> throw RuntimeException("Wrong arguments for $command")
            expectedArgument == null -> throw RuntimeException("Unknown command")
            expectedArgument != args[1] -> throw RuntimeException("Wrong arguments for $command")
        }
    }
}
