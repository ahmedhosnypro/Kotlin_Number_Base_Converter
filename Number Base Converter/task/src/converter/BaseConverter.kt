package converter

import java.math.BigDecimal

object BaseConverter {
    fun convert(strNum: String, sourceBase: Int, targetBase: Int): String {
        val parts = strNum.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val integerPart = parts[0]
        var fractionalPart = ""
        return if (parts.size == 2) {
            fractionalPart = parts[1]
            val decimal: BigDecimal = DecimalConverter.toDecimalConverter(integerPart, fractionalPart, sourceBase, true)
            DecimalConverter.fromDecimalConvert(decimal, targetBase, true)
        } else {
            val decimal: BigDecimal =
                DecimalConverter.toDecimalConverter(integerPart, fractionalPart, sourceBase, false)
            DecimalConverter.fromDecimalConvert(decimal, targetBase, false)
        }
    }
}