package com.testing.testing2.function.logarithmic;

import com.testing.testing2.exception.ArgumentIsOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

class LnTest {

    private Ln ln;

    @BeforeEach
    void setUp() {
        ln = new Ln(0.00001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -5.9, -0.002})
    void lnXBelowOrEqualsZero(double value) {
        assertThrows(ArgumentIsOutOfRangeException.class, () -> ln.calculate(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1,Math.E, 0.5, 1.7, 0.34, 5, 10})
    void lnTest(double value) {
        double expected = new BigDecimal(Double.toString(Math.log(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        double actual = new BigDecimal(Double.toString(ln.calculate(value)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expected, actual);
    }

    @Test
    void nanArgTest() {
        assertTrue(Double.isNaN(ln.calculate(NaN)));
    }

}