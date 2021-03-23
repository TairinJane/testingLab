package lab2

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import lab2.log.*
import lab2.trigonometry.*
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.tan

enum class MockType {
    BASE,
    DERIVATIVE,
    MAIN,
    FINAL
}

fun getFunctionSystemMock(mockType: MockType, x: Double): FunctionSystem {
    when (mockType) {
        MockType.MAIN -> {
            val fn1Mock: F1 = mock {
                on { value(x) } doReturn refF1(x)
            }
            val fn2Mock: F2 = mock {
                on { value(x) } doReturn refF2(x)
            }
            return FunctionSystem(fn1Mock, fn2Mock)
        }
        MockType.DERIVATIVE -> {
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
            return FunctionSystem(fn1, fn2)
        }
        MockType.BASE -> {
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