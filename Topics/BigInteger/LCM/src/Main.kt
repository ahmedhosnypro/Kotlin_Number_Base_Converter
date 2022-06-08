import java.math.BigInteger

fun main() {
    println(
        readln().toBigInteger().lcm(readln().toBigInteger())
    )
}

fun BigInteger.lcm(b: BigInteger): BigInteger = this * b / this.gcd(b)