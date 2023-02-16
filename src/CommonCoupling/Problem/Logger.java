package CommonCoupling.Problem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static File logFile;

    public static void log(String message) {
        try {
            FileWriter writer = new FileWriter(logFile, true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}