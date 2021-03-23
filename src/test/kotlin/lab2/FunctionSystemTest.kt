package lab2

import lab2.trigonometry.equalsDelta
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class FunctionSystemTest {

    private val precision = 0.0001

    @ParameterizedTest(name = "f1-f2 at {0}")
    @DisplayName("Mock f1 and f2")
    @ValueSource(doubles = [0.0, 1.0, 2.0])
    fun `mock main functions`(x: Double) {
        val functionSystem = getFunctionSystemMock(MockType.MAIN, x)
        val refValue = refFunctionSystem(x)
        val testValue = functionSystem.value(x)
        Assertions.assertTrue(
            testValue.equalsDelta(refValue, precision),
            "test $testValue != $refValue with delta = $precision"
        )
    }

    @ParameterizedTest(name = "derivative at {0}")
    @DisplayName("Mock derivative functions")
    @ValueSource(doubles = [0.0, 1.0, 2.0])
    fun `mock derivative functions`(x: Double) {
        val functionSystem = getFunctionSystemMock(MockType.DERIVATIVE, x)
        val refValue = refFunctionSystem(x)
        val testValue = functionSystem.value(x)
        Assertions.assertTrue(
            testValue.equalsDelta(refValue, precision),
            "test $testValue != $refValue with delta = $precision"
        )
    }

    @ParameterizedTest(name = "cos and ln at {0}")
    @DisplayName("Mock cos and ln")
    @ValueSource(doubles = [0.0, 1.0, 2.0])
    fun `mock base functions`(x: Double) {
        val functionSystem = getFunctionSystemMock(MockType.BASE, x)
        val refValue = refFunctionSystem(x)
        val testValue = functionSystem.value(x)
        Assertions.assertTrue(
            testValue.equalsDelta(refValue, precision),
            "test $testValue != $refValue with delta = $precision"
        )
    }

    @ParameterizedTest(name = "final at {0}")
    @DisplayName("No mocks")
    @ValueSource(doubles = [0.0, 1.0, 2.0])
    fun final(x: Double) {
        val functionSystem = getFunctionSystemMock(MockType.FINAL, x)
        val refValue = refFunctionSystem(x)
        val testValue = functionSystem.value(x)
        Assertions.assertTrue(
            testValue.equalsDelta(refValue, precision),
            "test $testValue != $refValue with delta = $precision"
        )
    }
}