package lab2

import lab2.trigonometry.equalsDelta
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI

class FunctionSystemTest {

    private val precision = 0.0001

    private fun testValueOnDifferentSteps(x: Double) {
        val mockTypes = MockType.values()
        for (type in mockTypes) {
            val mock = getFunctionSystemMock(type)
            val refValue = refFunctionSystem(x)
            val testValue = mock.value(x)
            Assertions.assertTrue(
                testValue.equalsDelta(refValue, precision),
                "test f($x) = %.8f != %.8f with precision = %.8f on mock $type".format(testValue, refValue, precision)
            )
        }
    }

    @ParameterizedTest(name = "Test log function at {0}")
    @ValueSource(doubles = [0.0, 0.76, 1.0, 1.365, 1.9974, 2.0, 6.12])
    fun `log function test`(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "x != pi*N, x != pi*(N + 1/2) at {0}")
    @ValueSource(doubles = [PI * -3, PI * (-10 + 1 / 2), PI * -345, PI * (-22 + 1 / 2)])
    fun `cos function test`(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "2(pi*N + 0.42773378844390394065) = 0, 2(pi*N + 1.1430625383509926786) = 0 at {0}")
    @ValueSource(doubles = [2*(PI * -5 + 0.42773378844390394065), 2*(PI * -63 + 1.1430625383509926786)])
    fun `f(x) = 0`(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "stationary 2(pi*N - 1.18487), 2(pi*N - 0.385931) at {0}")
    @ValueSource(doubles = [2*(PI* -8 - 1.18487), 2*(PI* -43 - 0.385931)])
    fun stationary(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "log for x < 1 at {0}")
    @ValueSource(doubles = [0.0058, 0.2, 0.84206])
    fun logDown(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "log for x > 1 at {0}")
    @ValueSource(doubles = [1.13, 4.863, 99.852])
    fun logUp(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @Test
    fun `log at x=1`() {
        testValueOnDifferentSteps(1.0)
    }
}