package com.testing.testing2.equation;

import com.testing.testing2.function.Function;

public class EquationSystem extends Function {

    private TrigEquation trigEquation;
    private LogEquation logEquation;

    public EquationSystem(TrigEquation trigEquation, LogEquation logEquation) {
        this.trigEquation = trigEquation;
        this.logEquation = logEquation;
    }

    @Override
    public double calculate(double x) {
        if (x <= 0) return trigEquation.calculate(x);
        return logEquation.calculate(x);
    }

    @Override
    public String getName() {
        return "equationSystem(x)";
    }

}
