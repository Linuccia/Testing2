package com.testing.testing2.function.logarithmic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogBaseTest {

    private Ln ln;
    private LogBase log5;
    private LogBase log10;

    @BeforeEach
    void setUp() {
        ln = Mockito.mock(Ln.class);
        when(ln.calculate(5)).thenReturn(Math.log(5));
        when(ln.calculate(10)).thenReturn(Math.log(10));
        log5 = new LogBase(ln, 5);
        log10 = new LogBase(ln, 10);
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.3, 1, 100, 22, 7.04, 33.333})
    void log5Test(double value) {
        when(ln.calculate(value)).thenReturn(Math.log(value));
        double expected = new BigDecimal(Double.toString(Math.log(value)/Math.log(5)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(log5.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 1, 100, 22, 7.04, 33.333})
    void log10Test(double value) {
        when(ln.calculate(value)).thenReturn(Math.log(value));
        double expected = new BigDecimal(Double.toString(Math.log(value)/Math.log(10)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(log10.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

}