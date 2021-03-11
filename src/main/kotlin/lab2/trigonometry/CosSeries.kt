package lab2.trigonometry

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow

private fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i
    }
    return result
}

fun Double.equalsDelta(other: Double, delta: Double = 0.0001) = abs(this - other) < delta

fun cosToTaylorSeries(x: Double, a: Double, termsCount: Int): Double {
    var result = 0.0
    for (i in 0 until termsCount) {
        result += cosTaylorSeriesTerm(x, a, i)
    }
    return result
}

fun cosTaylorSeriesTerm(x: Double, a: Double, n: Int): Double {
    return (x - a).pow(n) * cos(a + PI * n / 2) / factorial(n)
}

fun cos(x: Double): Double {
    return cosToTaylorSeries(x, x, 10)
}