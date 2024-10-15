package net.salesianos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataSegregator {
  public static Map<String, Set<String>> segregatePopulationByCharacteristic(String[] characteristics,
      ArrayList<String> dataArrayList,
      String characteristic) {
    Map<String, Set<String>> segregatedPopulation = new HashMap<>();
    String[] data = new String[dataArrayList.size()];
    dataArrayList.toArray(data);
    int characteristicIndex = 0;
    data[0].indexOf(characteristic, 0);
    String[] personCharacteristics = data[0].split(",");
    for (int i = 0; i < personCharacteristics.length; i++) {
      if (personCharacteristics[i].equals(characteristic)) {
        characteristicIndex = i;
      }
    }
    for (String person : data) {
      String[] splitedPerson = person.split(",");
      if (!splitedPerson[characteristicIndex].equals(characteristic)) {
        Set<String> people = segregatedPopulation.computeIfAbsent(splitedPerson[characteristicIndex],
            k -> new HashSet<>());
        people.add(person);
      }
    }
    return segregatedPopulation;
  }
}
