package com.testing.testing2.equation;

import com.testing.testing2.function.trigonometric.Sin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TrigEquationTest {

    private Sin sin;
    private TrigEquation equation;

    @BeforeEach
    void setUp() {
        sin = Mockito.mock(Sin.class);
        equation = new TrigEquation(sin);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/2, -Math.PI/3, -Math.PI/4,
            -Math.PI/6, 0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2})
    void trigEquationTest(Double value) {
        when(sin.calculate(value)).thenReturn(Math.sin(value));
        double expected = new BigDecimal(Double.toString(Math.pow(Math.sin(value), 2)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(equation.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

}