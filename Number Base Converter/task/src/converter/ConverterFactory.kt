package converter

import java.util.*

object ConverterFactory {
    fun createConverter(): BaseConverter {
        return DecimalConverter()
    }

    fun createConverter(baseAsString: String): BaseConverter {
//        return when (baseAsString.lowercase(Locale.getDefault())) {
//            "decimal" ->
        return converter.DecimalConverter()
//            else -> null
//        }
    }

    fun createConverter(baseAsInt: Int): BaseConverter {
//        return when (baseAsInt) {
//            10 ->
        return converter.DecimalConverter()
//            else -> null

    }
}