package lab2.log

import lab2.Function
import lab2.trigonometry.equalsDelta

open class LogN(private val ln: Ln, private val n: Double) : Function {
    override fun value(x: Double): Double = if (x.equalsDelta(1.0)) 0.0 else ln.value(x) / ln.value(n)

}

open class Log2(private val ln: Ln) : Function {

    private val logN = LogN(ln, 2.0)

    override fun value(x: Double): Double = logN.value(x)

}

open class Log5(private val ln: Ln) : Function {

    private val logN = LogN(ln, 5.0)

    override fun value(x: Double): Double = logN.value(x)

}

open class Log10(private val ln: Ln) : Function {

    private val logN = LogN(ln, 10.0)

    override fun value(x: Double): Double = logN.value(x)

}