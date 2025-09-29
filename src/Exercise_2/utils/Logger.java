//src/utils/Logger.java


package Exercise_2.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static final String LOG_FILE = "app.log";

    public static void info(String message) {
        writeLog("INFO", message);
    }

    public static void error(String message) {
        writeLog("ERROR", message);
    }

    private static void writeLog(String level, String message) {
        String timestamp = LocalDateTime.now().toString();
        String logMessage = "[" + timestamp + "] [" + level + "] " + message + "\n";

        try {
            File file = new File(LOG_FILE);

            if (!file.exists()) {
                file.createNewFile();
            }

            // open file in append mode
            FileWriter writer = new FileWriter(file, true);
            writer.write(logMessage);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
