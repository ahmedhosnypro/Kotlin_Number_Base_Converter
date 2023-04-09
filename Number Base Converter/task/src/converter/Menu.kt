package converter

import converter.BaseConverter.convert
import java.util.*

object Menu {
    private var scanner = Scanner(System.`in`)
    fun fstLvlMenu() {
        print(
            "Enter two numbers in format:" +
                    " {source base} {target base} (To quit type /exit) "
        )
        val input = scanner.nextLine().trim { it <= ' ' }
        if (input != "/exit") {
            val bases = input.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (bases.size == 2) {
                var sourceBase: Int
                var targetBase: Int
                while (true) {
                    try {
                        sourceBase = bases[0].toInt()
                        targetBase = bases[1].toInt()
                        sndLvlMenu(sourceBase, targetBase)
                        break
                    } catch (e: NumberFormatException) {
                        println("Invalid Base, try again")
                        fstLvlMenu()
                    }
                }
            } else {
                println("Invalid Base, try again")
                fstLvlMenu()
            }
        }
    }

    private fun sndLvlMenu(sourceBase: Int, targetBase: Int) {
        print(
            "Enter number in base " + sourceBase + " to convert" +
                    " to base " + targetBase + " (To go back type /back) "
        )
        val input = scanner.nextLine().trim { it <= ' ' }.uppercase(Locale.getDefault())
        if ((input == "/BACK")) {
            fstLvlMenu()
        } else {
            while (true) {
                if (input.matches(".*\\d\\w.*|.*\\w.*".toRegex())) {
                    val out = convert(input, sourceBase, targetBase)
                    println("Conversion result: $out")
                    println()
                    sndLvlMenu(sourceBase, targetBase)
                    break
                } else {
                    println("Invalid Number, try again")
                    sndLvlMenu(sourceBase, targetBase)
                }
            }
        }
    }
}