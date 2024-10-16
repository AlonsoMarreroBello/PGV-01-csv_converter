package net.salesianos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataExtractor {
  public static ArrayList<String> getDataFromCSV(String route) throws IOException {
    ArrayList<String> dataList = new ArrayList<>();
    BufferedReader bReader = new BufferedReader(new FileReader(new File(route)));
    String line = "";
    while ((line = bReader.readLine()) != null) {
      dataList.add(line);
    }
    bReader.close();
    return dataList;
  }

  public static ArrayList<String> getDataFromTxt(String folderRoute) throws IOException {
    File folder = new File(folderRoute);
    File[] listOfFiles = folder.listFiles();

    ArrayList<String> fileData = new ArrayList<>();
    for (File file : listOfFiles) {
      try (BufferedReader bReader = new BufferedReader(new FileReader(folderRoute + "/" + file.getName()))) {
        String line = "";
        while ((line = bReader.readLine()) != null) {
          fileData.add(line);
        }
      }
    }
    return fileData;
  }
}
