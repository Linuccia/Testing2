package com.testing.testing2.equation;

import com.testing.testing2.function.Function;
import com.testing.testing2.function.trigonometric.Sin;

public class TrigEquation extends Function {

    private Sin sin;

    public TrigEquation(Sin sin) {
        this.sin = sin;
    }

    @Override
    public double calculate(double x) {
        return Math.pow(sin.calculate(x), 2);
    }

    @Override
    public String getName() {
        return "trigEquation(x)";
    }

}
