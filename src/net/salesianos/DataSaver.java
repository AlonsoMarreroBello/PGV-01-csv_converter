package net.salesianos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataSaver {
    private static void saveDataToCSV(String[] args) throws IOException {
        String outputRoute = args[1];
        try (BufferedWriter bWritter = new BufferedWriter(new FileWriter(outputRoute))) {
            bWritter.write(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        saveDataToCSV(args);
    }
}
