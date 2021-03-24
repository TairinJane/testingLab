package lab2

import lab2.log.Log10
import lab2.log.Log2
import lab2.log.Log5
import lab2.trigonometry.Cos
import lab2.trigonometry.Cot
import lab2.trigonometry.Sec
import kotlin.math.cos
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.tan

//x <= 0 : ((cot(x) - sec(x)) + cos(x))
//x > 0 : (((((log_2(x) + log_2(x)) * log_5(x)) * log_10(x)) ^ 3) ^ 2)

//x != pi*N, x != pi*(N + 1/2)
//x > 0

//log series domain = (0, 2)

open class F1(private val cos: Cos, private val cot: Cot, private val sec: Sec) : Function {
    override fun value(x: Double): Double = cot.value(x) - sec.value(x) + cos.value(x)
}

open class F2(private val log2: Log2, private val log5: Log5, private val log10: Log10) : Function {
    override fun value(x: Double): Double = ((log2.value(x) + log2.value(x)) * log5.value(x) * log10.value(x)).pow(3).pow(2)
}

class FunctionSystem(private val f1: F1, private val f2: F2) : Function {
    override fun value(x: Double): Double = if (x <= 0) f1.value(x) else f2.value(x)
}

fun refF1(x: Double): Double {
    return 1/ tan(x) - 1/ cos(x) + cos(x)
}

fun refF2(x: Double): Double {
    return ((log(x, 2.0) + log(x, 2.0)) * log(x, 5.0) * log(x, 10.0)).pow(3).pow(2)
}

fun refFunctionSystem(x: Double): Double {
    return if (x <= 0) refF1(x)
    else refF2(x)
}