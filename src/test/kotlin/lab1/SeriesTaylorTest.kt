package lab1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI
import kotlin.math.cos

class SeriesTaylorTest {

    private val termsCount = 9
    private val maxError = 0.000001

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 2 * PI * 8, -2 * PI * 176])
    fun `2piN = 1`(x: Double) {
        val cosSeries = cosToTaylorSeries(x, x, termsCount)
        Assertions.assertTrue(
            cosSeries.equalsDelta(1.0, maxError),
            "cos(%.8f) = %.8f != 1 with error %.8f".format(x, cosSeries, maxError)
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [PI / 2, PI / 2 + 10 * PI, PI / 2 - 987 * PI])
    fun `pi div 2 + piN = 0`(x: Double) {
        val cosSeries = cosToTaylorSeries(x, x, termsCount)
        Assertions.assertTrue(
            cosSeries.equalsDelta(0.0, maxError),
            "cos(%.8f) = %.8f != 0 with error %.8f".format(x, cosSeries, maxError)
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [PI, PI + 2 * PI * 314, PI - 2 * PI * 9])
    fun `pi + 2piN = -1`(x: Double) {
        val cosSeries = cosToTaylorSeries(x, x, termsCount)
        Assertions.assertTrue(
            cosSeries.equalsDelta(-1.0, maxError),
            "cos(%.8f) = %.8f != -1 with error %.8f".format(x, cosSeries, maxError)
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [2 * PI + 0.2, 2 * PI * 7 + PI / 2 + 0.6, PI / 2 - 10 * PI - 0.0006461])
    fun `2piN to pi + 2piN`(x: Double) {
        val cosSeries = cosToTaylorSeries(x, x, termsCount)
        val cosIdeal = cos(x)
        Assertions.assertTrue(
            cosSeries.equalsDelta(cosIdeal, maxError),
            "cos(%.8f) = %.8f != %.8f with error %.8f".format(x, cosSeries, cosIdeal, maxError)
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [PI + 2 * PI + 0.4, 2 * PI + 2 * PI * 15 - 0.782, PI / 2 - 37 * PI + 1.124])
    fun `pi + 2piN to 2pi + 2piN`(x: Double) {
        val cosSeries = cosToTaylorSeries(x, x, termsCount)
        val cosIdeal = cos(x)
        Assertions.assertTrue(
            cosSeries.equalsDelta(cosIdeal, maxError),
            "cos(%.8f) = %.8f != %.8f with error %.8f".format(x, cosSeries, cosIdeal, maxError)
        )
    }
}