package lab2.trigonometry

import kotlin.math.pow
import kotlin.math.sqrt

fun sin(x: Double): Double {
    return sqrt(1 - cosTaylorSeriesTerm(x, x, 10).pow(2))
}

fun cot(x: Double): Double {
    return cosTaylorSeriesTerm(x, x, 10)/ sin(x)
}

fun sec(x: Double): Double {
    return 1/ cosTaylorSeriesTerm(x, x, 10)
}