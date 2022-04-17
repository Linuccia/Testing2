package com.testing.testing2.equation;

import com.testing.testing2.exception.ArgumentIsOutOfRangeException;
import com.testing.testing2.function.logarithmic.Ln;
import com.testing.testing2.function.logarithmic.LogBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LogEquationTest {

    private Ln ln;
    private LogBase log5;
    private LogBase log10;
    private LogEquation equation;

    @BeforeEach
    void setUp() {
        ln = Mockito.mock(Ln.class);
        log5 = Mockito.mock(LogBase.class);
        log10 = Mockito.mock(LogBase.class);
        equation = new LogEquation(ln, log5, log10);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 100, 22, 7.04, 33.333})
    void logEquationTest(Double value) {
        when(ln.calculate(value)).thenReturn(Math.log(value));
        when(log5.calculate(value)).thenReturn(Math.log(value)/Math.log(5));
        when(log10.calculate(value)).thenReturn(Math.log(value)/Math.log(10));
        double expected = (Math.pow(Math.pow(ln.calculate(value), 3), 2) +
                log10.calculate(value) - log5.calculate(value) / log10.calculate(value)) /
                ((log5.calculate(value) - log10.calculate(value)) / log10.calculate(value));
        expected = new BigDecimal(Double.toString(expected))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(equation.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }
    
    @Test
    void logEquationOutOfRangeTest() {
        assertThrows(ArgumentIsOutOfRangeException.class, () -> equation.calculate(1));
    }


}