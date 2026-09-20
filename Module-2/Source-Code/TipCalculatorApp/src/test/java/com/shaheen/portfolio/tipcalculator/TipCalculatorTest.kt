package com.shaheen.portfolio.tipcalculator

import org.junit.Assert.assertEquals
import org.junit.Test

class TipCalculatorTest {
    @Test
    fun calculateTip_15Percent_returnsExpectedValue() {
        assertEquals(15.0, calculateTip(100.0, 15.0, false), 0.001)
    }

    @Test
    fun calculateTip_roundUp_returnsWholeValue() {
        assertEquals(16.0, calculateTip(101.0, 15.0, true), 0.001)
    }
}
