package com.testing.testing2.function.logarithmic;

import com.testing.testing2.function.Function;

public class LogBase extends Function {

    private int base;
    private Ln ln;
    private double lnBase;

    public LogBase(Ln ln, int base) {
        if (base <= 0) throw new IllegalArgumentException("Base cannot be <= 0");
        this.base = base;
        this.ln = ln;
        lnBase = ln.calculate(base);
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / lnBase;
    }

    @Override
    public String getName() {
        return "log" + base + "(x)";
    }

}
