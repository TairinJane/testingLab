package lab2.log

fun logN(x: Double, n: Double): Double {
    return ln(x)/ ln(n)
}

fun log2(x: Double): Double {
    return logN(x, 2.0)
}

fun log5(x: Double): Double {
    return logN(x, 5.0)
}

fun log10(x: Double): Double {
    return logN(x, 10.0)
}