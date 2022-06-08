package converter

import java.util.*

object Starter {
    fun start() {
        val input = InputParser.parse()
        val convertFactory = ConvertFactory()
        val converter: BaseConverter? = convertFactory.createConverter(10)
        val out: String? = converter?.convert(input[0], input[1])
        print("Conversion result: ")
        println(out)
    }
}


object InputParser {
    private val scanner = Scanner(System.`in`)
    fun parse(): IntArray {
        val out = IntArray(2)
        print("Enter number in decimal system: ")
        while (true) {
            try {
                out[0] = scanner.next().trim { it <= ' ' }.toInt()
                break
            } catch (ignored: NumberFormatException) {
                // ignored
            }
        }
        print("Enter target base: ")
        while (true) {
            try {
                out[1] = scanner.next().trim { it <= ' ' }.toInt()
                break
            } catch (ignored: NumberFormatException) {
                // ignored
            }
        }
        return out
    }
}