package lab2.log

import lab2.Function
import kotlin.math.ln
import kotlin.math.pow

open class Ln : Function {
    override fun value(x: Double): Double = ln(x)
}

class LnSeries(private val termsCount: Int = 10) : Ln() {
    private fun lnTerm(x: Double, n: Int): Double {
        return (-1.0).pow(n) * (x - 1).pow(n + 1) / (n + 1)
    }

    override fun value(x: Double): Double {
        var result = 0.0
        for (i in 0 until termsCount) {
            result += lnTerm(x, i)
        }
        return -result
    }
}