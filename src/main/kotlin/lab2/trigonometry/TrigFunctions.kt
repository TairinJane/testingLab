package lab2.trigonometry

import lab2.Function
import kotlin.math.pow
import kotlin.math.sqrt

open class Sin(private val cos: Cos): Function {
    override fun value(x: Double): Double = sqrt(1 - cos.value(x).pow(2))
}

open class Cot(private val cos: Cos, private val sin: Sin): Function {
    override fun value(x: Double): Double = cos.value(x)/ sin.value(x)
}

open class Sec(private val cos: Cos): Function {
    override fun value(x: Double): Double = 1/ cos.value(x)
}