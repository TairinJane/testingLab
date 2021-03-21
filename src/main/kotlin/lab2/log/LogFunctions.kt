package lab2.log

fun logN(x: Double, n: Double, ln: (Double) -> Double): Double {
    return ln(x)/ ln(n)
}

fun log2(x: Double, ln: (Double) -> Double): Double {
    return logN(x, 2.0, ln)
}

fun log5(x: Double, ln: (Double) -> Double): Double {
    return logN(x, 5.0, ln)
}

fun log10(x: Double, ln: (Double) -> Double): Double {
    return logN(x, 10.0, ln)
}