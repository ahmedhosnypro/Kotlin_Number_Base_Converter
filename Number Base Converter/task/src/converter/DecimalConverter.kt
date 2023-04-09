package converter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.util.*


internal object DecimalConverter {
    fun toDecimalConverter(
        integerPart: String,
        fractionalPart: String,
        sourceBase: Int,
        isFractional: Boolean
    ): BigDecimal {
        val base = BigDecimal.valueOf(sourceBase.toLong())
        var result = BigDecimal.ZERO
        //converting integer part
        var tmpResult: BigDecimal
        var digit = 0
        var power = 0
        for (i in integerPart.length - 1 downTo 0) {
            val ch = integerPart[i]
            when (ch.code) {
                in 48..57 -> digit = ch.code - 48
                in 65..90 -> digit = ch.code - 55
                in 97..122 -> digit = ch.code - 87
            }

            tmpResult = BigDecimal.valueOf(digit.toLong()).multiply(base.pow(power))
            result = result.add(BigDecimal(tmpResult.toString()))
            power++
        }

        //converting fractional part
        if (isFractional) {
            power = 1
            for (element in fractionalPart) {
                when (element.code) {
                    in 48..57 -> digit = element.code - 48
                    in 65..90 -> digit = element.code - 55
                    in 97..122 -> digit = element.code - 87
                }

                val baseOfPower = base.pow(power)
                tmpResult = BigDecimal.valueOf(digit.toLong()).divide(baseOfPower, 10, RoundingMode.CEILING)
                result = result.add(BigDecimal(tmpResult.toString()))
                power++
            }
        }
        return result
    }

    fun fromDecimalConvert(decimalNumber: BigDecimal, targetBase: Int, isFractional: Boolean): String {
        val bigDecimalBase = BigDecimal.valueOf(targetBase.toLong())
        val bigIntBase = BigInteger.valueOf(targetBase.toLong())

        //converting integer part
        val integer = decimalNumber.toBigInteger()
        var Quotient = integer
        var Remainder: Int
        val list = LinkedList<String>()
        while (Quotient.compareTo(BigInteger.ZERO) != 0) {
            Remainder = Quotient.remainder(bigIntBase).toString().toInt()
            Quotient = Quotient.divide(BigInteger.valueOf(targetBase.toLong()))
            if (Remainder <= 9) {
                list.addFirst(Remainder.toString())
            } else {
                val ch = (Remainder + 55).toChar()
                list.addFirst(ch.toString())
            }
        }
        val out = StringBuilder()
        for (N in list) {
            out.append(N)
        }

        //converting fractional part
        if (isFractional) {
            val integerPart = BigDecimal(integer.toString())
            var fractionalPart = decimalNumber.subtract(integerPart)
            val strConvertedFraction = StringBuilder(".")
            while (true) {
                val remainder = fractionalPart.multiply(bigDecimalBase)
                val remainderInt = remainder.toBigInteger()
                val decimalRemainderInt = BigDecimal(remainderInt.toString())
                val remainderFraction = remainder.subtract(decimalRemainderInt)
                Remainder = remainderInt.toString().toInt()
                if (Remainder <= 9) {
                    strConvertedFraction.append(Remainder)
                } else {
                    val ch = (Remainder + 55).toChar()
                    strConvertedFraction.append(ch)
                }
                fractionalPart = if (remainderFraction == BigDecimal.ZERO || strConvertedFraction.length == 6) {
                    break
                } else {
                    remainderFraction
                }
            }
            if (strConvertedFraction.length < 6) {
                val length = strConvertedFraction.length
                val empty = 7 - length
                strConvertedFraction.append("0".repeat(Math.max(0, empty)))
            }
            out.append(strConvertedFraction)
        }
        return out.toString()
    }
}