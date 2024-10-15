package net.salesianos;

import java.util.ArrayList;

public class DataFormatter {
  public static String formatDataToCSV(ArrayList<String> dataFromFiles) {
    String header = "Name,Nationality,Age,Profession\n";
    String body = "";
    for (String personData : dataFromFiles) {
      String replaceData = personData.replaceAll("; ", ",");
      body += replaceData + "\n";
    }
    String csvContent = header + body;
    return csvContent;
  }
}
