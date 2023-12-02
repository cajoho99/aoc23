import java.io.File
import java.util.HashMap

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
//    val answer1 = solutionPart1(input)
//    val answer2 = solutionPart2(input)
//    println("Answer part 1: $answer1")
//    println("Answer part 2: $answer2")
}

fun solutionPart1(input: List<String>): Int {
    val numbers: List<Int> = input.map {
        val (digits, nonDigits) = it.partition { c -> c.isDigit() }
        val first = digits.first()
        val last = digits.last()
        val number = first.toString() + last.toString()
        number.toInt()
    }

    return numbers.sum()
}

fun solutionPart2(input: List<String>): Int {
    val textToNumber = HashMap<String, Int>()
    textToNumber.putAll(listOf(
            Pair("zero", 0),
            Pair("one", 1),
            Pair("two", 2),
            Pair("three", 3),
            Pair("four", 4),
            Pair("five", 5),
            Pair("six", 6),
            Pair("seven", 7),
            Pair("eight", 8),
            Pair("nine", 9),
    ))

    val numbersList = input.map {
        val res = mutableListOf<Int>()
        for (i in it.indices step 1) {
            if (i < it.length && it[i].isDigit()) {
                res.add(it[i].digitToInt())
            } else {
                for (k in textToNumber.keys) {
                    if (it.drop(i).startsWith(k)) {
                        textToNumber[k]?.let { it1 -> res.add(it1) }
                    }
                }
            }
        }
        res
    }

    val result = numbersList.map {
        println(it)
        val v = it.first().toString() + it.last().toString()
        v.toInt()
    }.sum()

    return result
}