package converter

import java.util.*

abstract class BaseConverter {
    abstract fun convert(input: Int, targetBase: Int): String?
    abstract fun toBinary(input: Int): String?
    abstract fun toOctal(input: Int): String?
    abstract fun toDecimal(input: Int): String?
    abstract fun toHex(input: Int): String?
}

internal class ConvertFactory {

    fun createConverter(inputBase: Int): BaseConverter? {
        return when (inputBase) {
            10 -> DecimalConverter()
            else -> null
        }
    }
}

internal class DecimalConverter : BaseConverter() {
    override fun convert(input: Int, targetBase: Int): String {
        return when (targetBase) {
            2 -> toBinary(input)
            8 -> toOctal(input)
            16 -> toHex(input)
            else -> input.toString()
        }
    }

    private fun normalConvert(input: Int, targetBase: Int): String {
        val out = StringBuilder()
        val list = LinkedList<Int>()
        var quotient = input
        var remainder: Int
        while (quotient > 0) {
            remainder = quotient % targetBase
            quotient /= targetBase
            list.addFirst(remainder)
        }
        for (N in list) {
            out.append(N)
        }
        return out.toString()
    }

    override fun toBinary(input: Int): String {
        return normalConvert(input, 2)
    }

    override fun toOctal(input: Int): String {
        return normalConvert(input, 8)
    }

    override fun toDecimal(input: Int): String {
        return input.toString()
    }

    override fun toHex(input: Int): String {
        val out = StringBuilder()
        val list = LinkedList<String>()
        var quotient = input
        var remainder: Int
        val map = mapOf(
            10 to "A", 11 to "B", 12 to "C", 13 to "D", 14 to "E", 15 to "F"
        )
        while (quotient > 0) {
            remainder = quotient % 16
            quotient /= 16
            val v = map.getOrDefault(remainder, remainder.toString())
            list.addFirst(v)
        }
        for (N in list) {
            out.append(N)
        }
        return out.toString()
    }
}