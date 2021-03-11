package lab2.log

import kotlin.math.pow

fun ln(x: Double): Double {
    var result = 0.0
    for (i in 0 until 10) {
        result += lnTerm(x, i)
    }
    return -result
}

fun lnTerm(x: Double, n: Int): Double {
    return (-1.0).pow(n) * (1 - +x).pow(n) / n
}