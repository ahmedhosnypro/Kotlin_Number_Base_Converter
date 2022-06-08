fun main() {
    val bigInt1 = readln().toBigInteger()
    val bigInt2 = readln().toBigInteger()

    val sum = bigInt1 + bigInt2

    println("${bigInt1 * 100.toBigInteger() / sum}% ${bigInt2 * 100.toBigInteger() / sum}%")
}