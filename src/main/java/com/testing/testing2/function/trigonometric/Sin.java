package com.testing.testing2.function.trigonometric;

import com.testing.testing2.function.Function;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;

public class Sin extends Function {

    private double eps;

    public Sin(double eps) {
        if (eps <= 0) throw new IllegalArgumentException("Accuracy must be > 0");
        this.eps = eps;
    }

    @Override
    public double calculate(double x) {
        if (isNaN(x)) return NaN;
        x = simplify(x);
        double prev = 0;
        double cur = 10;
        double res = 0;
        int k = 1;
        int sign = 1;
        double factorial = 1.0;
        while (Math.abs(cur - prev) >= eps) {
            factorial *= k;
            prev = cur;
            cur = sign * Math.pow(x, k) / factorial;
            res += cur;
            factorial *= k + 1;
            sign *= -1;
            k += 2;
        }
        return res;
    }

    private double simplify(double x) {
        if (x >= -Math.PI && x <= Math.PI) return x;
        x = x % (Math.PI * 2);
        if (x > Math.PI) return x - 2 * Math.PI;
        if (x < -Math.PI) return x + 2 * Math.PI;
        return x;
    }

    @Override
    public String getName() {
        return "sin(x)";
    }

}
