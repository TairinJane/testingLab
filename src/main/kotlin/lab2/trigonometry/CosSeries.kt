package lab2.trigonometry

import lab2.Function
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow

open class Cos : Function {
    override fun value(x: Double): Double = cos(x)
}

class CosSeries(private val termsCount: Int = 10) : Cos() {
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
        for (i in 0 until termsCount) {
            result += cosTaylorSeriesTerm(x, x, i)
        }
        return result
    }

}

fun Double.equalsDelta(other: Double, delta: Double = 0.0001) =
    abs(this - other) < delta || this.isNaN() && other.isNaN() || this.isInfinite() && other.isInfinite()