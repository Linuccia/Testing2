package com.testing.testing2;

import com.testing.testing2.exception.ArgumentIsOutOfRangeException;
import com.testing.testing2.function.Function;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public static void writeResult(Function function, double fromX, double toX, double step) {
        File file = new File("src/main/resources/" + function.getName() + ".csv");
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("X, " + function.getName() + "\n");
            double x = fromX;
            while (x <= toX) {
                try {
                    writer.write(x + ", " + function.calculate(x) + "\n");
                } catch (ArgumentIsOutOfRangeException e) {
                    System.out.println(e.getMessage());
                }
                x += step;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
