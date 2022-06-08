package converter

abstract class BaseConverter {
    var hexMap = mapOf(
        10 to "A", 11 to "B", 12 to "C",
        13 to "D", 14 to "E", 15 to "F"
    )
    var reversedHexMap = mapOf(
        "A" to 10, "B" to 11, "C" to 12,
        "D" to 13, "E" to 14, "F" to 15
    )

    abstract fun fromBaseConvert(decimalNumber: String, targetBase: String): String?

    abstract fun toBaseConverter(sourceNumber: String, sourceBase: String): String?
}