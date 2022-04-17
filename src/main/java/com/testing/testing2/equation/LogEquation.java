package com.testing.testing2.equation;

import com.testing.testing2.exception.ArgumentIsOutOfRangeException;
import com.testing.testing2.function.Function;
import com.testing.testing2.function.logarithmic.Ln;
import com.testing.testing2.function.logarithmic.LogBase;

public class LogEquation extends Function {

    private Ln ln;
    private LogBase log5;
    private LogBase log10;

    public LogEquation(Ln ln, LogBase log5, LogBase log10) {
        this.ln = ln;
        this.log5 = log5;
        this.log10 = log10;
    }

    @Override
    public double calculate(double x) {
        if (x == 1) throw new ArgumentIsOutOfRangeException("X is out" +
                " of range:\n" +
                "x != 1");
        return (Math.pow(Math.pow(ln.calculate(x), 3), 2) +
                log10.calculate(x) - log5.calculate(x) / log10.calculate(x)) /
                ((log5.calculate(x) - log10.calculate(x)) / log10.calculate(x));
    }

    @Override
    public String getName() {
        return "logEquation(x)";
    }

}
