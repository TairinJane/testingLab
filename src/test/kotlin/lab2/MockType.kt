package lab2

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import lab2.log.*
import lab2.trigonometry.*
import org.mockito.ArgumentMatchers.anyDouble
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.tan

enum class MockType {
    MAIN,
    DERIVATIVE,
    BASE,
    FINAL
}

fun getFunctionSystemMock(mockType: MockType): FunctionSystem {
    when (mockType) {
        MockType.MAIN -> {
            val fn1Mock: F1 = mock {
                on { value(anyDouble()) } doAnswer { i -> refF1(i.arguments[0] as Double) }
            }
            val fn2Mock: F2 = mock {
                on { value(anyDouble()) } doAnswer { i -> refF2(i.arguments[0] as Double) }
            }
            return FunctionSystem(fn1Mock, fn2Mock)
        }
        MockType.DERIVATIVE -> {
            val cosMock: Cos = mock {
                on { value(anyDouble()) } doAnswer { i -> cos(i.arguments[0] as Double) }
            }
            val cotMock: Cot = mock {
                on { value(anyDouble()) } doAnswer { i -> 1 / tan(i.arguments[0] as Double) }
            }
            val secMock: Sec = mock {
                on { value(anyDouble()) } doAnswer { i -> 1 / cos(i.arguments[0] as Double) }
            }
            val log2Mock: Log2 = mock {
                on { value(anyDouble()) } doAnswer { i ->  log(i.arguments[0] as Double, 2.0) }
            }
            val log5Mock: Log5 = mock {
                on { value(anyDouble()) } doAnswer { i -> log(i.arguments[0] as Double, 5.0) }
            }
            val log10Mock: Log10 = mock {
                on { value(anyDouble()) } doAnswer { i -> log(i.arguments[0] as Double, 10.0) }
            }
            val fn1 = F1(cosMock, cotMock, secMock)
            val fn2 = F2(log2Mock, log5Mock, log10Mock)
            return FunctionSystem(fn1, fn2)
        }
        MockType.BASE -> {
            val cosMock: Cos = mock {
                on { value(anyDouble()) } doAnswer { i -> cos(i.arguments[0] as Double) }
            }
            val lnMock: Ln = mock {
                on { value(anyDouble()) } doAnswer { i -> ln(i.arguments[0] as Double) }
            }
            val sin = Sin(cosMock)
            val cot = Cot(cosMock, sin)
            val sec = Sec(cosMock)
            val log5 = Log5(lnMock)
            val log2 = Log2(lnMock)
            val log10 = Log10(lnMock)
            val fn1 = F1(cosMock, cot, sec)
            val fn2 = F2(log2, log5, log10)
            return FunctionSystem(fn1, fn2)
        }
        MockType.FINAL -> {
            val cos = CosSeries(0.00001)
            val ln = LnSeries(0.00001)
            val sin = Sin(cos)
            val cot = Cot(cos, sin)
            val sec = Sec(cos)
            val log5 = Log5(ln)
            val log2 = Log2(ln)
            val log10 = Log10(ln)
            val fn1 = F1(cos, cot, sec)
            val fn2 = F2(log2, log5, log10)
            return FunctionSystem(fn1, fn2)
        }
    }
}

fun writeMocksCsv() {
    val mockTypes = MockType.values()
    for (type in mockTypes) {
        val mock = getFunctionSystemMock(type)
    }
}