import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.pow

class SeriesTest {

    private val termsCount = 9
    private val a = 0.0
    private val machineError = (2.0).pow(-52)

    @Test
    fun maclaurinSeriesEquals() {
        for (i in -31415..31415 step 1000) {
            val x = (i / 10000).toDouble()
            val cosIdeal = cos(x)
            val cosSeries = cosToMaclaurinSeries(x, termsCount)
            val maxError = cosMaclaurinSeriesTerm(x, termsCount - 1)
            Assertions.assertEquals(
                true,
                cosSeries.equalsDelta(cosIdeal, max(maxError, machineError)),
                "cos($x) = $cosSeries != $cosIdeal with error $maxError"
            )
        }
    }

    @Test
    fun taylorSeriesEquals() {
        for (i in -31415..31415 step 1000) {
            val x = (i / 10000).toDouble()
            val cosIdeal = cos(x)
            val cosSeries = cosToTaylorSeries(x, a, termsCount)
            val maxError = cosTaylorSeriesTerm(x, a, termsCount - 1)
            Assertions.assertEquals(
                true,
                cosSeries.equalsDelta(cosIdeal, max(maxError, machineError)),
                "cos($x) = $cosSeries != $cosIdeal with error $maxError"
            )
        }
    }
}