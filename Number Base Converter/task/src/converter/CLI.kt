package converter

import kotlin.system.exitProcess

object CLI {
    fun start() {
        var toContinue = true
        while (toContinue) {
            toContinue = runCommand()
        }
    }

    private fun runCommand(): Boolean {
        val command = selectCommand()

        val decimalConverter: BaseConverter = ConverterFactory.createConverter()

        val out = StringBuilder()
        return when (command) {
            Command.FROM -> {
                val decimalNumber = InputReader.getDecimalString()
                val targetBase = InputReader.targetBaseAsString()
                out.append("Conversion result: ")
                out.append(decimalConverter.fromBaseConvert(decimalNumber, targetBase))
                println(out.toString())
                true
            }
            Command.TO -> {
                val sourceNumber = InputReader.getHexValue()
                val sourceBase = InputReader.sourceBaseAsString()
                out.append("Conversion to decimal result: ")
                out.append(decimalConverter.toBaseConverter(sourceNumber, sourceBase))
                println(out)
                true
            }
            Command.EXIT -> exitProcess(0)
        }
    }


    private fun selectCommand(): Command {
        print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ")
        val inputCommand: String = readln().replace("/", "").uppercase()

        while (true) {
            try {
                return Command.valueOf(inputCommand)
            } catch (e: IllegalArgumentException) {
                // ignored
            }
        }
    }
}