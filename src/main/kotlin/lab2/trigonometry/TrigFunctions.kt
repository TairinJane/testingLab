package lab2.trigonometry

import kotlin.math.pow
import kotlin.math.sqrt

fun sin(x: Double, cos: (Double) -> Double): Double {
    return sqrt(1 - cos(x).pow(2))
}

fun cot(x: Double, cos: (Double) -> Double, sin: (Double) -> Double): Double {
    return cos(x)/ sin(x)
}

fun sec(x: Double, cos: (Double) -> Double): Double {
    return 1/ cos(x)
}