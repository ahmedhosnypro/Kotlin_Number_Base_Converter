package converter

object BaseConverter {
    fun convert(strNum: String, sourceBase: Int, targetBase: Int): String {
        val decimal = DecimalConverter.toDecimalConverter(strNum, sourceBase)
        return DecimalConverter.fromDecimalConvert(decimal, targetBase)
    }
}