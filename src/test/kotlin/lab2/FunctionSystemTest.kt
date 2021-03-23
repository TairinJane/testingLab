package lab2

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import lab2.log.*
import lab2.trigonometry.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.tan

class FunctionSystemTest {

    @ParameterizedTest(name = "f1-f2 at {0}")
    @DisplayName("Mock f1 and f2")
    @ValueSource(doubles = [0.0, 1.0, 2.0])
    fun `mock main functions`(x: Double) {
        val fn1Mock: F1 = mock {
            on { value(x) } doReturn refF1(x)
        }
        val fn2Mock: F2 = mock {
            on { value(x) } doReturn refF2(x)
        }
        val functionSystem = FunctionSystem(fn1Mock, fn2Mock)
        val refValue = refFunctionSystem(x)
        val testValue = functionSystem.value(x)
        Assertions.assertTrue(testValue.equalsDelta(refValue), "test $testValue != $refValue with delta = 0.0001")
    }

    @ParameterizedTest(name = "derivative at {0}")
    @DisplayName("Mock derivative functions")
    @ValueSource(doubles = [0.0, 1.0, 2.0])
    fun `mock derivative functions`(x: Double) {
        val cosMock: Cos = mock {
            on { value(x) } doReturn cos(x)
        }
        val cotMock: Cot = mock {
            on { value(x) } doReturn 1 / tan(x)
        }
        val secMock: Sec = mock {
            on { value(x) } doReturn 1 / cos(x)
        }
        val log2Mock: Log2 = mock {
            on { value(x) } doReturn log(x, 2.0)
        }
        val log5Mock: Log5 = mock {
            on { value(x) } doReturn log(x, 5.0)
        }
        val log10Mock: Log10 = mock {
            on { value(x) } doReturn log(x, 10.0)
        }
        val fn1 = F1(cosMock, cotMock, secMock)
        val fn2 = F2(log2Mock, log5Mock, log10Mock)
        val functionSystem = FunctionSystem(fn1, fn2)
        val refValue = refFunctionSystem(x)
        val testValue = functionSystem.value(x)
        Assertions.assertTrue(testValue.equalsDelta(refValue), "test $testValue != $refValue with delta = 0.0001")
    }

    @ParameterizedTest(name = "cos and ln at {0}")
    @DisplayName("Mock cos and ln")
    @ValueSource(doubles = [0.0, 1.0, 2.0])
    fun `mock base functions`(x: Double) {
        val cosMock: Cos = mock {
            on { value(x) } doReturn cos(x)
        }
        val lnMock: Ln = mock {
            on { value(x) } doReturn ln(x)
        }
        val sin = Sin(cosMock)
        val cot = Cot(cosMock, sin)
        val sec = Sec(cosMock)
        val log5 = Log5(lnMock)
        val log2 = Log2(lnMock)
        val log10 = Log10(lnMock)
        val fn1 = F1(cosMock, cot, sec)
        val fn2 = F2(log2, log5, log10)
        val functionSystem = FunctionSystem(fn1, fn2)
        val refValue = refFunctionSystem(x)
        val testValue = functionSystem.value(x)
        Assertions.assertTrue(testValue.equalsDelta(refValue), "test $testValue != $refValue with delta = 0.0001")
    }

    @ParameterizedTest(name = "final at {0}")
    @DisplayName("No mocks")
    @ValueSource(doubles = [0.0, 1.0, 2.0])
    fun final(x: Double) {
        val cos = CosSeries(10)
        val ln = LnSeries(10)
        val sin = Sin(cos)
        val cot = Cot(cos, sin)
        val sec = Sec(cos)
        val log5 = Log5(ln)
        val log2 = Log2(ln)
        val log10 = Log10(ln)
        val fn1 = F1(cos, cot, sec)
        val fn2 = F2(log2, log5, log10)
        val functionSystem = FunctionSystem(fn1, fn2)
        val refValue = refFunctionSystem(x)
        val testValue = functionSystem.value(x)
        Assertions.assertTrue(testValue.equalsDelta(refValue), "test $testValue != $refValue with delta = 0.0001")
    }
}