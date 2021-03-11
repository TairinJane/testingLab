package lab2

import lab2.log.log10
import lab2.log.log2
import lab2.log.log5
import lab2.trigonometry.cos
import lab2.trigonometry.cot
import lab2.trigonometry.sec
import kotlin.math.pow

//x <= 0 : ((cot(x) - sec(x)) + cos(x))
//x > 0 : (((((log_2(x) + log_2(x)) * log_5(x)) * log_10(x)) ^ 3) ^ 2)

fun f1(x: Double): Double {
    return cot(x) - sec(x) + cos(x)
}

fun f2(x: Double): Double {
    return ((log2(x) + log2(x)) * log5(x) * log10(x)).pow(3.0).pow(2.0)
}

fun functionSystem(x: Double): Double {
    return if (x > 0) f2(x) else f1(x)
}