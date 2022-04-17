package com.testing.testing2.function.logarithmic;

import com.testing.testing2.exception.ArgumentIsOutOfRangeException;
import com.testing.testing2.function.Function;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;

public class Ln extends Function {

    private double eps;

    public Ln(double eps) {
        if (eps <= 0) throw new IllegalArgumentException("Accuracy must be > 0");
        this.eps = eps;
    }

    @Override
    public double calculate(double x) {
        if (isNaN(x)) return NaN;
        if (x <= 0) throw new ArgumentIsOutOfRangeException("X cannot be <= 0");
        if (Math.abs(-1 + x) > 1) return calculate(x / 2) + calculate(2);
        double prev = expansion(x, 1);
        double cur = expansion(x, 2);
        double res = prev + cur;
        int k = 3;
        while (Math.abs(cur - prev) >= eps) {
            prev = cur;
            cur = expansion(x, k);
            res += cur;
            k++;
        }
        return -res;
    }

    private double expansion(double x, int k) {
        return Math.pow(-1, k) * (Math.pow(x - 1, k) / k);
    }

    @Override
    public String getName() {
        return "ln(x)";
    }

}
