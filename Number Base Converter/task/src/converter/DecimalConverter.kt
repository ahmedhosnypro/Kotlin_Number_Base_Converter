package converter

import java.util.*
import kotlin.math.pow

class DecimalConverter : BaseConverter() {
    override fun fromBaseConvert(decimalNumber: String, targetBase: String): String {
        var quotient = decimalNumber.toInt()
        val base = targetBase.toInt()
        var remainder: Int

        val list = LinkedList<String>()

        while (quotient > 0) {
            remainder = quotient % base
            quotient /= base
            if (base == 2 || base == 8 || base == 10) {
                list.addFirst(remainder.toString())
            } else if (base == 16) {
                list.addFirst(hexMap[remainder] ?: remainder.toString())
            }
        }
        val out = StringBuilder()
        for (N in list) {
            out.append(N)
        }
        return out.toString()
    }

    override fun toBaseConverter(sourceNumber: String, sourceBase: String): String {
        val base = sourceBase.toInt()
        var result = 0

        val is2Or8 = base == 2 || base == 8
        if (is2Or8) {
            var digit = sourceNumber.toInt()
            var power = 0
            var tmpResult: Int
            while (digit > 0) {
                val n = digit % 10
                tmpResult = (n * base.toDouble().pow(power.toDouble())).toInt()
                result += tmpResult
                digit /= 10
                power++
            }
        } else if (base == 16) {
            var digit: Int
            var tmpResult: Int
            for ((power, i) in (sourceNumber.length - 1 downTo 0).withIndex()) {
                val ch = sourceNumber[i]
                digit = reversedHexMap[ch.toString()] ?: (ch.code - 48)
                tmpResult = (digit * base.toDouble().pow(power.toDouble())).toInt()
                result += tmpResult
            }
        }
        return result.toString()
    }
}