import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    val bd1 = readln().toBigDecimal()
    val bd2 = readln().toBigDecimal()
    val bd3 = readln().toBigDecimal()

    val sum = bd1 + bd2 + bd3
    println((sum.divide(BigDecimal(3), 0, RoundingMode.DOWN)))
}