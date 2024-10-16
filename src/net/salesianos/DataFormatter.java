package net.salesianos;

import java.util.ArrayList;

public class DataFormatter {
  public static String formatDataToCSV(ArrayList<String> dataFromFiles) {
    String header = "Name,Nationality,Age,Profession\n";
    String body = "";
    for (int i = 0; i < dataFromFiles.size(); i++) {
      char[] chars = dataFromFiles.get(i).toLowerCase().toCharArray();
      boolean found = false;
      for (int j = 0; j < chars.length; j++) {
        if (!found && Character.isLetter(chars[j])) {
          chars[j] = Character.toUpperCase(chars[j]);
          found = true;
        } else if (Character.isWhitespace(chars[j]) || chars[j] == '.' || chars[j] == '\'' || chars[j] == ',') {
          found = false;
        }
      }

      String replaceData = String.valueOf(chars);
      body += replaceData + "\n";
    }

    StringBuilder newBody = new StringBuilder(body);
    if (newBody.length() > 0) {
      newBody.setLength(newBody.length() - 1);
    }
    String csvContent = header + newBody.toString();
    return csvContent;
  }
}
