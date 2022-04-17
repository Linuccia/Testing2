package com.testing.testing2.equation;


import com.testing.testing2.exception.ArgumentIsOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class EquationSystemTest {

    private TrigEquation trigEquation;
    private LogEquation logEquation;
    private EquationSystem system;

    @BeforeEach
    void setUp() {
        trigEquation = Mockito.mock(TrigEquation.class);
        logEquation = Mockito.mock(LogEquation.class);
        system = new EquationSystem(trigEquation, logEquation);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.3, -100, -22, -7.04, -33.333, 0})
    void firstEquationTest(Double value) {
        when(trigEquation.calculate(value)).thenReturn(Math.pow(Math.sin(value), 2));
        double expected = trigEquation.calculate(value);
        expected = new BigDecimal(Double.toString(expected))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(system.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 100, 22, 7.04, 33.333})
    void secondEquationTest(Double value) {
        when(logEquation.calculate(value)).thenReturn((Math.pow(Math.pow(Math.log(value), 3), 2) +
                Math.log(value)/Math.log(10) - Math.log(value)/Math.log(5) / Math.log(value))/Math.log(10) /
                ((Math.log(value)/Math.log(5) - Math.log(value))/Math.log(10) / Math.log(value))/Math.log(10));
        double expected = logEquation.calculate(value);
        expected = new BigDecimal(Double.toString(expected))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(system.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

    @Test
    void secondEquationOutOfRangeTest() {
        when(logEquation.calculate(1)).thenThrow(new ArgumentIsOutOfRangeException(""));
        assertThrows(ArgumentIsOutOfRangeException.class, () -> system.calculate(1));
    }

}