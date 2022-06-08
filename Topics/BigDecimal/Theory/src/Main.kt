import java.math.BigDecimal
import java.math.RoundingMode

// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    var number = BigDecimal("2.001")
    number += BigDecimal.ONE
    number = -number
    number.setScale(1, RoundingMode.HALF_DOWN)
    print(number)
}

