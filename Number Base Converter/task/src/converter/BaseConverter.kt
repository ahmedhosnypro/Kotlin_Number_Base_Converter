package converter

object BaseConverter {
    fun convert(strNum: String, sourceBase: Int, targetBase: Int): String {
        val parts = strNum.split(".")
        val number = parts[0]
        var fractionalPart = ""
        var isFractional = false

        if (parts.size == 2) {
            fractionalPart = parts[1]
            isFractional = true
        }

        val base10 =
            ToDecimalBaseConverter.convert(number, fractionalPart, sourceBase, isFractional)
        return FromDecimalBaseConverter.convert(base10, targetBase, isFractional)
    }
}