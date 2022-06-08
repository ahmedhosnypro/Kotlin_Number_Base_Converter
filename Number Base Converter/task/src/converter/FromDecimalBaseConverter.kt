package converter

import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

object FromDecimalBaseConverter {
    fun convert(base10: BigDecimal, targetBase: Int, isFractional: Boolean): String {
        //converting integer part
        val convertedIntPart = convertIntegerPart(base10, targetBase)

        //converting fractional part
        val convertedFracPart = convertFractionalPart(isFractional, base10, targetBase)
        return convertedIntPart + convertedFracPart
    }

    private fun convertIntegerPart(decimalNumber: BigDecimal, targetBase: Int): String {
        var quotient = decimalNumber.toBigInteger()
        var remainder: Int

        val list = LinkedList<String>()

        //quotient.compareTo(BigInteger.ZERO) != 0
        while (quotient != BigInteger.ZERO) {

            remainder = (quotient % (targetBase.toBigInteger())).toString().toInt()

            quotient /= targetBase.toBigInteger()

            if (remainder <= 9) {
                list.addFirst(remainder.toString())
            } else {
                val ch = (remainder + 55).toChar()
                list.addFirst(ch.toString())
            }
        }

        val out = StringBuilder()
        list.forEach { out.append(it) }
        return out.toString()
    }

    private fun convertFractionalPart(isFractional: Boolean, decimalNumber: BigDecimal, targetBase: Int): String {
        if (isFractional) {
            val convertedFracPart = StringBuilder(".")
            var fractionalPart = decimalNumber - decimalNumber.toBigInteger().toBigDecimal()

            var remainder: Int
            while (true) {
                val tmpRemainder = fractionalPart * targetBase.toBigDecimal()
                val intRemainder = tmpRemainder.toBigInteger()
                val decimalRemainderInt = intRemainder.toBigDecimal()
                val fracRemainder = tmpRemainder.subtract(decimalRemainderInt)

                remainder = intRemainder.toInt()
                if (remainder <= 9) {
                    convertedFracPart.append(remainder)
                } else {
                    val ch = (remainder + 55).toChar()
                    convertedFracPart.append(ch)
                }
                fractionalPart = if (fracRemainder == BigDecimal.ZERO || convertedFracPart.length == 6) {
                    break
                } else {
                    fracRemainder
                }
            }
            if (convertedFracPart.length < 6) {
                val length = convertedFracPart.length
                val empty = 7 - length
                convertedFracPart.append("0".repeat(0.coerceAtLeast(empty)))
            }
            convertedFracPart.append(convertedFracPart)
            return convertedFracPart.toString()
        }

        return ""
    }
}