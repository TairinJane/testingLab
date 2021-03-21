package lab2

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import lab2.log.ln
import lab2.log.log10
import lab2.log.log2
import lab2.log.log5
import lab2.trigonometry.cos
import lab2.trigonometry.cot
import lab2.trigonometry.sec
import lab2.trigonometry.sin
import org.junit.jupiter.api.Test

class FunctionSystemTest {

    private val cosMock: (Double) -> Double = mock()

    @Test
    fun `mock main functions`() {
        val fn1Mock: (Double) -> Double = mock()
        val fn2Mock: (Double) -> Double = mock()
        functionSystem(1.0, fn1Mock, fn2Mock)
    }

    @Test
    fun `mock derivative functions`() {
        val cotMock: (Double) -> Double = mock()
        val secMock: (Double) -> Double = mock()
        val ln2Mock: (Double) -> Double = mock()
        val ln5Mock: (Double) -> Double = mock()
        val ln10Mock: (Double) -> Double = mock()
        val fn1 = { x: Double -> f1(x, cosMock, cotMock, secMock) }
        val fn2 = { x: Double -> f2(x, ln2Mock, ln5Mock, ln10Mock) }
        functionSystem(1.0, fn1, fn2)
    }

    @Test
    fun `mock base functions`() {
        val lnMock: (Double) -> Double = mock()
        val sinMock = { x: Double -> sin(x, cosMock) }
        val cotMock = { x: Double -> cot(x, cosMock, sinMock) }
        val secMock = { x: Double -> sec(x, cosMock) }
        val ln2Mock = { x: Double -> log2(x, lnMock) }
        val ln5Mock = { x: Double -> log5(x, lnMock) }
        val ln10Mock = { x: Double -> log10(x, lnMock) }
        val fn1 = { x: Double -> f1(x, cosMock, cotMock, secMock) }
        val fn2 = { x: Double -> f2(x, ln2Mock, ln5Mock, ln10Mock) }
        functionSystem(1.0, fn1, fn2)
    }

    @Test
    fun final() {
        val lnFn = { x: Double -> ln(x) }
        val cosFn = { x: Double -> cos(x) }
        val sinFn = { x: Double -> sin(x, cosFn) }
        val cotFn = { x: Double -> cot(x, cosFn, sinFn) }
        val secFn = { x: Double -> sec(x, cosFn) }
        val ln2Fn = { x: Double -> log2(x, lnFn) }
        val ln5Fn = { x: Double -> log5(x, lnFn) }
        val ln10Fn = { x: Double -> log10(x, lnFn) }
        val fn1 = { x: Double -> f1(x, cosFn, cotFn, secFn) }
        val fn2 = { x: Double -> f2(x, ln2Fn, ln5Fn, ln10Fn) }
        functionSystem(1.0, fn1, fn2)
    }
}