package converter

import java.math.BigInteger
import java.util.*


internal object DecimalConverter {
    fun toDecimalConverter(strNum: String, sourceBase: Int): BigInteger {
        var result = BigInteger.ZERO
        var digit = 0
        val base = BigInteger.valueOf(sourceBase.toLong())
        var tmpResult: BigInteger?
        for ((power, i) in (strNum.length - 1 downTo 0).withIndex()) {
            val ch = strNum[i]
            when (ch.code) {
                in 48..57 -> digit = ch.code - 48
                in 65..90 -> digit = ch.code - 55
                in 97..122 -> digit = ch.code - 87
            }

            tmpResult = BigInteger.valueOf(digit.toLong()).multiply(base.pow(power))
            result = result.add(tmpResult)
        }
        return result
    }

    fun fromDecimalConvert(decimalNumber: BigInteger, targetBase: Int): String {
        var quotient = decimalNumber
        var remainder: Int
        val list = LinkedList<String>()
        do {
            remainder = quotient.remainder(BigInteger.valueOf(targetBase.toLong())).toString().toInt()
            quotient = quotient.divide(BigInteger.valueOf(targetBase.toLong()))
            if (remainder <= 9) {
                list.addFirst(remainder.toString())
            } else {
                val ch = (remainder + 55).toChar()
                list.addFirst(ch.toString())
            }
        } while (quotient != BigInteger.ZERO);
        val out = StringBuilder()
        for (n in list) {
            out.append(n)
        }
        return out.toString()
    }
}