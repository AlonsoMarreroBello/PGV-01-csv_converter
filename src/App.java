import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import net.salesianos.DataExtractor;
import net.salesianos.DataFormatter;
import net.salesianos.DataSegregator;
import net.salesianos.utils.Menu;
import net.salesianos.utils.ProcessLauncher;

public class App {
  public static void main(String[] args) throws Exception {
    final String ERROR_ROUTE = "logs\\";
    Scanner SC = new Scanner(System.in);
    Menu.showMenu();
    switch (SC.nextLine()) {
      case "1":
        System.out.println("Introduzca la ruta del CSV: ");
        String csvRoute = SC.nextLine();
        System.out.println("Introduzca la ruta de salida:");
        String outputRoute = SC.nextLine();
        String[] characteristics = { "Name", "Nationality", "Age", "Profession" };
        String characteristic = "";
        boolean keepAsking = true;
        while (keepAsking) {
          System.out.println("Introduzca la caracteristica por la cual separar: \nName, Nationality, Age o Profession");
          characteristic = SC.nextLine();
          for (int i = 0; i < characteristics.length; i++) {
            if (characteristics[i].contains(characteristic)) {
              keepAsking = false;
            }
          }
        }
        ArrayList<String> list = DataExtractor.getDataFromCSV(csvRoute);
        Map<String, Set<String>> segregatedData = DataSegregator.segregatePopulationByCharacteristic(
            characteristics, list, characteristic);
        ArrayList<Process> processes = new ArrayList<>();
        for (String string : segregatedData.keySet()) {
          if (string.trim() != characteristic) {
            StringBuilder data = new StringBuilder();
            for (String setData : segregatedData.get(string)) {
              data.append(setData).append("\n");
            }
            if (data.length() > 0) {
              data.setLength(data.length() - 1);
            }
            String filename = string.replaceAll(" ", "");
            Process process = ProcessLauncher.startProcess("src\\net\\salesianos\\DataSaver.java", data.toString(),
                outputRoute + "/" + filename + ".txt",
                ERROR_ROUTE + filename + "_err.log");
            processes.add(process);
          }
        }
        for (Process process : processes) {
          process.waitFor();
        }
        System.out.printf("Se han guardado los datos en " + outputRoute + " ! :D");
        break;
      case "2":
        System.out.println("Introduzca la ruta de la carpeta");
        String folderRoute = SC.nextLine();
        ArrayList<String> dataFolder = DataExtractor.getDataFromTxt(folderRoute);
        System.out.println("Introduzca la ruta de salida junto con el nombre del archivo csv");
        String outputPath = SC.nextLine();

        String formattedData = DataFormatter.formatDataToCSV(dataFolder);

        ProcessLauncher.startProcess("src\\net\\salesianos\\DataSaver.java", formattedData, outputPath,
            ERROR_ROUTE + "people_err.log");
        System.out.printf("Se han guardado los datos en " + outputPath + " ! :D");
        break;
      default:
        break;
    }
    SC.close();
  }
}
