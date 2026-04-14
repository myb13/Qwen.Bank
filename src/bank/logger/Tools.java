package bank.logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Tools {
    static String LOG_DIR = "D:\\Dev\\Java\\Qwen.Bank\\logs";

    static void put(String logName, String message) {
        try (var logFile = new FileWriter(LOG_DIR + "\\" + logName, true)) {
            logFile.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Не удалось записать лог (" + logName + "): " + e);
        }
    }

    static void read(String logName){
        String s;
        try (var logFile = new BufferedReader(new FileReader(LOG_DIR + "\\" + logName))) {
            while((s = logFile.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать лог (" + logName + "): " + e);
        }
    }
}
