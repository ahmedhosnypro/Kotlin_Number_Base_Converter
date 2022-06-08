fun main() {
    val input = readln()

    var unique = 0
    for (ch in input) {
        if (input.toList().stream().filter { c: Char -> c == ch }.count().toInt() == 1) {
            unique += 1
        }
    }

    print(unique)
}