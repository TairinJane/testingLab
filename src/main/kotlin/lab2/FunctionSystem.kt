package lab2

import kotlin.math.pow

//x <= 0 : ((cot(x) - sec(x)) + cos(x))
//x > 0 : (((((log_2(x) + log_2(x)) * log_5(x)) * log_10(x)) ^ 3) ^ 2)

fun f1(x: Double, cos: (Double) -> Double, cot: (Double) -> Double, sec: (Double) -> Double): Double {
    return cot(x) - sec(x) + cos(x)
}

fun f2(x: Double, log2: (Double) -> Double, log5: (Double) -> Double, log10: (Double) -> Double): Double {
    return ((log2(x) + log2(x)) * log5(x) * log10(x)).pow(3.0).pow(2.0)
}

fun functionSystem(x: Double, f1: (Double) -> Double, f2: (Double) -> Double): Double {
    return if (x <= 0) f1(x) else f2(x)
}