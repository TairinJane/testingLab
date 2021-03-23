package lab2.trigonometry

import lab2.Function
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow

open class Cos : Function {
    override fun value(x: Double): Double = cos(x)
}

class CosSeries(private val precision: Double = 0.0001) : Cos() {
    private fun factorial(n: Int): Double {
        var result = 1.0
        for (i in 1..n) {
            result *= i
        }
        return result
    }

    private fun cosTaylorSeriesTerm(x: Double, a: Double, n: Int): Double {
        return (x - a).pow(n) * cos(a + PI * n / 2) / factorial(n)
    }

    override fun value(x: Double): Double {
        var result = 0.0
        var i = 0
        var term = cosTaylorSeriesTerm(x, x, i)
        do {
            result += term
            i++
            term = cosTaylorSeriesTerm(x, x, i)
        } while (term > precision)
        return result
    }

}

fun Double.equalsDelta(other: Double, delta: Double = 0.0001) =
    abs(this - other) < delta || this.isNaN() && other.isNaN() || this.isInfinite() && other.isInfinite()