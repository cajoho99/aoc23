import java.io.File
import kotlin.math.max

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
//    val input = File("day02/input.txt").readLines()
//    val answer1 = solutionPart1(input)
//    val answer2 = solutionPart2(input)
//    println("Answer part 1: $answer1")
//    println("Answer part 2: $answer2")
}

enum class Color {
    RED,
    BLUE,
    GREEN
}

class Draw(val color: Color, val value: Int)

class Set(val draws: List<Draw>)

class Game(val id: Int, val sets: List<Set>)

fun solutionPart1(input: List<String>): Int {
    val maxValues = HashMap<Color, Int>()
    listOf(Pair(Color.RED, 12), Pair(Color.GREEN, 13), Pair(Color.BLUE, 14)).map { p -> maxValues.put(p.first, p.second) }
    val games = parseInput(input)
    val validGames = games.filter { game ->
        game.sets.all { set -> !isSetInvalid(set, maxValues) }
    }

    return validGames.sumOf { g -> g.id }
}

private fun isSetInvalid(set: Set, maxValues: HashMap<Color, Int>): Boolean {
    val red = set.draws.filter { draw -> draw.color == Color.RED }.sumOf { d -> d.value }
    val green = set.draws.filter { draw -> draw.color == Color.GREEN }.sumOf { d -> d.value }
    val blue = set.draws.filter { draw -> draw.color == Color.BLUE }.sumOf { d -> d.value }

    val isValid = red > maxValues[Color.RED]!! || green > maxValues[Color.GREEN]!! || blue > maxValues[Color.BLUE]!!
    return isValid
}

private fun parseInput(input: List<String>) = input.mapIndexed() { index, row ->
    Game(index + 1,
            row.split(":").last().split(";").map { s ->
                Set(
                        s.split(",").map { p ->
                            val pick = p.trim().split(" ")
                            val c = when (pick.last()) {
                                "red" -> Color.RED
                                "green" -> Color.GREEN
                                "blue" -> Color.BLUE
                                else -> throw Exception()
                            }
                            val v = pick.first().toInt()

                            Draw(c, v)
                        })
            })
}

fun solutionPart2(input: List<String>): Int {
    val games = parseInput(input)
    val result = games.sumOf { game ->
        getPowerForGame(game.sets)
    }
    return result
}

private fun getPowerForGame(sets: List<Set>): Int {
    fun getMinValueForColor(color: Color) = sets.map{
        set ->
        set.draws.filter {
            draw -> draw.color == color
        }.map { draw -> draw.value }
    }.filter { i -> i.isNotEmpty() }.map { i-> i.max() }.max()

    val red = getMinValueForColor(Color.RED)
    val green = getMinValueForColor(Color.GREEN)
    val blue = getMinValueForColor(Color.BLUE)

    return red * green * blue
}
