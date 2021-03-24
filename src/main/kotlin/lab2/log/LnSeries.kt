package lab2.log

import lab2.Function
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.pow

open class Ln : Function {
    override fun value(x: Double): Double = ln(x)
}

class LnSeries(private val precision: Double = 0.0001) : Ln() {
    private fun lnTerm(x: Double, n: Int): Double {
        return (-1.0).pow(n) * (x - 1).pow(n) / n
    }

    override fun value(x: Double): Double {
        var result = 0.0
        var i = 1
        var term = lnTerm(x, i)
        do {
            result += term
            i++
            term = lnTerm(x, i)
        } while (abs(term) > precision && term.isFinite() && i < 100)
        return -result
    }
}