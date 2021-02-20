import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.cos

class SeriesTest {

    @Test
    fun seriesCosEquals() {
        for (x in -314..314 step 10) {
            Assertions.assertEquals(true, cos(x / 100.0).equalsDelta(cosToTaylorSeries(x / 100.0, 7)))
        }
    }
}