package com.testing.testing2.function.trigonometric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

class SinTest {

    private Sin sin;

    @BeforeEach
    void setUp() {
        sin = new Sin(0.00001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI/2, -Math.PI/3, -Math.PI/4,
            -Math.PI/6, 0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2})
    void rightHalfSinTest(Double value) {
        double expected = new BigDecimal(Double.toString(Math.sin(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(sin.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {4*Math.PI/6, 3*Math.PI/4,
            2*Math.PI/3, Math.PI, 7*Math.PI/6, 5*Math.PI/4, 5*Math.PI/3})
    void leftHalfSinTest(Double value) {
        double expected = new BigDecimal(Double.toString(Math.sin(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(sin.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {22, 32.7777, 4, 19, 0.15, 18, 1.3, 0.000001})
    void differentAnglesTest(Double value) {
        double expected = new BigDecimal(Double.toString(Math.sin(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(sin.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

    @Test
    void nanArgTest() {
        assertTrue(Double.isNaN(sin.calculate(NaN)));
    }

}