package lab2.log

import lab2.F1
import lab2.F2
import lab2.FunctionSystem
import lab2.trigonometry.CosSeries
import lab2.trigonometry.Cot
import lab2.trigonometry.Sec
import lab2.trigonometry.Sin

fun main() {
    /*val ln = LnSeries(10)
    for (i in -20..20) {
        println(ln.value(i / 10.0))
    }*/
    val testX = 1.0
    val cos = CosSeries(10)
    println(cos.value(testX))
    val ln = LnSeries(10)
    println(ln.value(testX))
    val sin = Sin(cos)
    println(sin.value(testX))
    val cot = Cot(cos, sin)
    println(cot.value(testX))
    val sec = Sec(cos)
    println(sec.value(testX))
    val log5 = Log5(ln)
    println(log5.value(testX))
    val log2 = Log2(ln)
    println(log2.value(testX))
    val log10 = Log10(ln)
    println(log10.value(testX))
    val fn1 = F1(cos, cot, sec)
    println(fn1.value(testX))
    val fn2 = F2(log2, log5, log10)
    println(fn2.value(testX))
    val functionSystem = FunctionSystem(fn1, fn2)
    println(functionSystem.value(testX))
}