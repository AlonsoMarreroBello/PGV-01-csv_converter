package net.salesianos.utils;

public class Menu {
  public static void showMenu() {
    String message = """
        ---------------------------
        |     MENU PRINCIPAL      |
        | 1.-Convertir CSV a TXT  |
        | 2.-Convertir TXT a CSV  |
        ---------------------------
        """;
    System.out.println(message);
  }
}
