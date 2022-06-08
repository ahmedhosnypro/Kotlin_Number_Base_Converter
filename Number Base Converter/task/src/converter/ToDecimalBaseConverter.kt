package converter

import java.math.BigDecimal
import java.math.RoundingMode

object ToDecimalBaseConverter  {
    fun convert(
        integerPart: String,
        fractionalPart: String,
        sourceBase: Int,
        isFractional: Boolean
    ): BigDecimal {
        //converting integer part
        val convertedIntPart = convertIntegerPart(integerPart, sourceBase)

        //converting fractional part
        return convertFractionalPart(isFractional, fractionalPart, sourceBase, convertedIntPart)
    }

    private fun convertIntegerPart(integerPart: String, sourceBase: Int): BigDecimal {
        var convertedIntPart = BigDecimal.ZERO
        var digit: Int
        val base = BigDecimal.valueOf(sourceBase.toLong())
        for ((power, i) in (integerPart.length - 1 downTo 0).withIndex()) {
            val ch = integerPart[i]
            digit = when (ch.code) {
                in 48..57 -> ch.code - 48
                in 65..90 -> ch.code - 55
                in 97..122 -> ch.code - 87
                else -> throw Exception("Error")
            }
            convertedIntPart += digit.toBigDecimal() * (base.pow(power))
        }
        return convertedIntPart
    }

     private fun convertFractionalPart(
        isFractional: Boolean, fractionalPart: String, sourceBase: Int, convertedIntPart: BigDecimal
    ): BigDecimal {
        if (!isFractional) {
            return convertedIntPart
        } else {
            val base = BigDecimal.valueOf(sourceBase.toLong())

            var digit: Int
            var power = 1

            var result = convertedIntPart

            for (element in fractionalPart) {
                digit = when (element.code) {
                    in 48..57 -> element.code - 48
                    in 65..90 -> element.code - 55
                    in 97..122 -> element.code - 87
                    else -> throw Exception("Error")
                }
                val baseOfPower = base.pow(power)
                result += digit.toBigDecimal().divide(baseOfPower, 10, RoundingMode.CEILING)
                power++
            }

            return result
        }
    }

}