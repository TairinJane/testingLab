import kotlin.math.cos

fun main(args: Array<String>) {
    for (x in -314..314) {
        println("cos(%.2f) = %.7f".format(x / 100.0, cos(x / 100.0)))
        println("series cos(%.2f) = %.7f".format(x / 100.0, cosToTaylorSeries(x / 100.0, 7)))
        println("actual == series? -> "+ cos(x / 100.0).equalsDelta(cosToTaylorSeries(x / 100.0, 7)))
        println()
    }
}