package converter

import converter.BaseConverter.convert
import kotlin.system.exitProcess

object CLI {
    fun fstLvlMenu() {
        print(
            "Enter two numbers in format:" +
                    " {source base} {target base} (To quit type /exit) "
        )

        val input: String = readln()
        if (input != "/exit") {
            val bases = input.split(" ")
            if (bases.size == 2) {
                var sourceBase: Int
                var targetBase: Int
                while (true) {
                    try {
                        sourceBase = bases[0].toInt()
                        targetBase = bases[1].toInt()
                        return sndLvlMenu(sourceBase, targetBase)
                    } catch (e: NumberFormatException) {
                        println("Invalid Base, try again")
                    }
                }
            } else {
                println("Invalid Base, try again")
                return fstLvlMenu()
            }
        }
        exitProcess(0)
    }

    private fun sndLvlMenu(sourceBase: Int, targetBase: Int) {
        print(
            "Enter number in base " + sourceBase + " to convert" +
                    " to base " + targetBase + " (To go back type /back) "
        )

        val input: String = readln().uppercase()

        if ((input == "/BACK")) {
           return fstLvlMenu()
        } else {
            while (true) {
                return if (input.matches(Regex(".*\\d.*|.*\\w.*+\\.+.*\\d.*|.*\\w.*"))) {
                    val out = convert(input, sourceBase, targetBase)
                    println("Conversion result: $out\n")

                    sndLvlMenu(sourceBase, targetBase)
                } else {
                    println("Invalid Number, try again")
                    sndLvlMenu(sourceBase, targetBase)
                }
            }
        }
    }
}