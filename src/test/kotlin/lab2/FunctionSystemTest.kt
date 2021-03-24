package lab2

import lab2.trigonometry.equalsDelta
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI

class FunctionSystemTest {

    private val precision = 0.0001

    private fun testValuesOnDifferentSteps(x: Double) {
        val mockTypes = MockType.values()
        for (type in mockTypes) {
            val mock = getFunctionSystemMock(type)
            val refValue = refFunctionSystem(x)
            val testValue = mock.value(x)
            Assertions.assertTrue(
                testValue.equalsDelta(refValue, precision),
                "test $testValue != $refValue with precision = $precision on mock $type"
            )
        }
    }

    @ParameterizedTest(name = "Test log function at {0}")
    @ValueSource(doubles = [0.0, 0.76, 1.0, 1.365, 1.9974, 2.0, 6.12])
    fun `log function test`(x: Double) {
        testValuesOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "x != pi*N, x != pi*N - pi/2 at {0}")
    @ValueSource(doubles = [-PI * 3, -PI * 10 - PI / 2, -PI * 345, -PI * 22 - PI / 2])
    fun `cos function test`(x: Double) {
        testValuesOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "2(pi*N + 0.42773378844390394065) = 0, 2(pi*N + 1.1430625383509926786) = 0 at {0}")
    @ValueSource(doubles = [2*(PI * -5 + 0.42773378844390394065), 2*(PI * -63 + 1.1430625383509926786)])
    fun `f(x) = 0`(x: Double) {
        testValuesOnDifferentSteps(x)
    }

    @ParameterizedTest(name = "stationary 2(pi*N - 1.18487), 2(pi*N - 0.385931) at {0}")
    @ValueSource(doubles = [2*(PI* -8 - 1.18487), 2*(PI* -43 - 0.385931)])
    fun stationary(x: Double) {
        testValuesOnDifferentSteps(x)
    }
}