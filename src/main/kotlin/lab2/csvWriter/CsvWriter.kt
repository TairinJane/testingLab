package lab2.csvWriter

import lab2.Function
import java.io.File

fun writeResultsToCsv(function: Function, from: Double, to: Double, step: Double, path: String = "results.csv", nums: Int = 8) {
    File(path).printWriter().use { out ->
        var x = from
        while (x < to) {
            out.println("%.${nums}f; %.${nums}f".format(x, function.value(x)))
            x += step
        }
    }
}