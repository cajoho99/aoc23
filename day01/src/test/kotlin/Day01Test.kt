import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Day01Test : StringSpec({
    val inputString = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent().lines()

    "solutionPart1 should return sum of input" {
        solutionPart1(inputString) shouldBe 142
    }
    val test2 =  """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent().lines()

    "solutionPart2 should return product of input" {
        solutionPart2(test2) shouldBe 281
    }
})