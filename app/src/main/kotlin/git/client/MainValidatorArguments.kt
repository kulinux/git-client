package git.client

object MainValidatorArguments {
    fun validate(args: Array<String>) =
        when {
            args.isEmpty() -> throw RuntimeException("Command not found")
            args[0] == "cat-file" -> validateCatFile(args)
            else -> throw RuntimeException("Unknown command")
        }

    private fun validateCatFile(args: Array<String>) {
        when {
            args.size < 3 -> throw RuntimeException("Wrong arguments for cat-file")
            args[1] != "-p" -> throw RuntimeException("Wrong arguments for cat-file")
        }
    }

}
