package CommonCoupling.Solution;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements LoggerInterface {
    private File logFile;

    public FileLogger(File logFile) {
        this.logFile = logFile;
    }

    public void log(String message) {
        try {
            FileWriter writer = new FileWriter(logFile, true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
