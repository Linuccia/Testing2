import com.testing.testing2.CSVWriter;
import com.testing.testing2.equation.EquationSystem;
import com.testing.testing2.equation.LogEquation;
import com.testing.testing2.equation.TrigEquation;
import com.testing.testing2.function.logarithmic.Ln;
import com.testing.testing2.function.logarithmic.LogBase;
import com.testing.testing2.function.trigonometric.Sin;

public class Main {

    public static void main(String[] args) {
        double eps = 0.00001;
        Sin sin = new Sin(eps);
        Ln ln = new Ln(eps);
        LogBase log5 = new LogBase(ln, 5);
        LogBase log10 = new LogBase(ln, 10);
        TrigEquation trigEquation = new TrigEquation(sin);
        LogEquation logEquation = new LogEquation(ln, log5, log10);
        EquationSystem system = new EquationSystem(trigEquation, logEquation);
        CSVWriter.writeResult(sin, -5, 5, 0.1);
        CSVWriter.writeResult(ln, 1, 5, 0.1);
        CSVWriter.writeResult(log5, 1, 10, 0.1);
        CSVWriter.writeResult(log10, 1, 20, 0.1);
        CSVWriter.writeResult(trigEquation, -5, 5, 0.1);
        CSVWriter.writeResult(logEquation, 1, 10, 0.1);
        CSVWriter.writeResult(system, -5, 5, 0.1);
    }

}
