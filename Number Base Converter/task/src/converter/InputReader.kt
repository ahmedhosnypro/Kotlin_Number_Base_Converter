package converter


object InputReader {
    fun getDecimalNum(): Int {
        print("Enter number in decimal system: ")
        return readInt()
    }

    fun getDecimalString(): String {
        print("Enter number in decimal system: ")
        return readNum()
    }

    fun getHexValue(): String {
        print("Enter source number: ")
        while (true) {
            try {
                val input = readln()
                return when {
                    input.matches(Regex("[A-F]")) -> continue
                    else -> input.uppercase()
                }
            } catch (ignored: NumberFormatException) {
                // ignored
            }
        }
    }

    fun targetBaseAsNum(): Int {
        print("Enter target base: ")
        return readInt()
    }

    fun targetBaseAsString(): String {
        print("Enter target base: ")
        return readNum()
    }

    fun sourceBaseAsNum(): Int {
        print("Enter source base: ")
        return readInt()
    }

    fun sourceBaseAsString(): String {
        print("Enter source base: ")
        return readNum()
    }

    private fun readInt(): Int {
        while (true) {
            try {
                return readln().toInt()
            } catch (ignored: NumberFormatException) {
                // ignored
            }
        }
    }


    private fun readNum(): String {
        while (true) {
            try {
                val input = readln()
                input.toInt()
                return input
            } catch (ignored: NumberFormatException) {
                // ignored
            }
        }
    }
}