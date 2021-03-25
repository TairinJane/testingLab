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

    @ParameterizedTest(name = "x != pi*N, x != pi*(N + 1/2) at {0}")
    @ValueSource(doubles = [PI * -3, PI * (-10 + 1 / 2), PI * -345, PI * (-22 + 1 / 2)])
    fun `cos function test`(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "2(pi*N + 0.42773378844390394065) = 0, 2(pi*N + 1.1430625383509926786) = 0 at {0}")
    @ValueSource(doubles = [2 * (PI * -5 + 0.42773378844390394065), 2 * (PI * -63 + 1.1430625383509926786)])
    fun `f(x) = 0`(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "stationary 2(pi*N - 1.18487), 2(pi*N - 0.385931) at {0}")
    @ValueSource(doubles = [2 * (PI * -8 - 1.18487), 2 * (PI * -43 - 0.385931)])
    fun stationary(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "(2(PIn - 0.385931); 2PIn) at {0}")
    @ValueSource(doubles = [-0.345, 2 * (PI * -9 - 0.2), 2 * (PI * -76 - 0.1082)])
    fun cosDownCurveLower(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "(PI(n + 1/2); 2(PIn - 0.385931)) at {0}")
    @ValueSource(doubles = [2 * (PI * -206 + 0.93), 2 * (PI * -9 + 0.7), 2 * (PI * -13 - 0.496)])
    fun cosUpCurveLower(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "(PIn; 2(PIn - 1.18487)) at {0}")
    @ValueSource(doubles = [-PI * 3 + 0.1, 2 * (PI * -7 - 1.6), 2 * (PI * -56 - 1.1982)])
    fun cosDownCurveUpper(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "Long curve down at {0}")
    @ValueSource(doubles = [2 * (PI * -142 + 0.3), PI * -16 + 0.35, 2 * (PI * -13 + 1.072), PI * -10 - 0.6])
    fun cosUpCurveUpper(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "log for x < 1 at {0}")
    @ValueSource(doubles = [0.0058, 0.2, 0.84206])
    fun logDown(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "log for x > 1 at {0}")
    @ValueSource(doubles = [1.13, 2.0, 4.863, 99.852])
    fun logUp(x: Double) {
        testValueOnDifferentSteps(x)
    }

    @Test
    fun `log at x = 1`() {
        testValueOnDifferentSteps(1.0)
    }
}