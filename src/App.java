import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import net.salesianos.DataExtractor;
import net.salesianos.DataSegregator;
import net.salesianos.utils.ProcessLauncher;

public class App {
  public static void main(String[] args) throws Exception {
    String csvRoute = "data\\input\\data.csv";
    String outputRoute = "data\\output\\";
    final String ERROR_ROUTE = "logs\\";
    String[] characteristics = { "Name", "Nationality", "Age", "Profession" };
    String characteristic = "Name";

    ArrayList<String> list = DataExtractor.getDataFromCSV(csvRoute);
    Map<String, Set<String>> segregatedData = DataSegregator.segregatePopulationByCharacteristic(
        characteristics, list, characteristic);
    ArrayList<Process> processes = new ArrayList<>();
    for (String string : segregatedData.keySet()) {
      if (string.trim() != characteristic) {
        String data = "";
        for (String charasteristic : characteristics) {
          data += charasteristic + ",";
        }
        for (String setData : segregatedData.get(string)) {
          data += "\n" + setData;
        }
        String filename = string.replaceAll(" ", "");
        Process process = ProcessLauncher.startProcess("src\\net\\salesianos\\DataSaver.java", data,
            outputRoute + filename + ".txt",
            ERROR_ROUTE + filename + "_err.log");
        processes.add(process);
      }
    }
    for (Process process : processes) {
      process.waitFor();
    }
  }
}
