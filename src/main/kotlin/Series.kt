import kotlin.math.abs
import kotlin.math.pow

fun cosToTaylorSeries(x: Double, termsCount: Int): Double {
    var result = 0.0
    for (i in 0 until termsCount) {
        result += cosSeriesTerm(x, i)
    }
    return result
}

private fun cosSeriesTerm(x: Double, n: Int): Double {
    return (-1.0).pow(n) * (x).pow(2 * n) / factorial(2 * n)
}

private fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i
    }
    return result
}

fun Double.equalsDelta(other: Double, delta: Double = 0.0001) = abs(this/other - 1) < delta