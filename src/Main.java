import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

  private static final String REGEX_LIST = "LIST";
  private static final String REGEX_NUMBER = "[+]?[0-9]+";
  private static final String REGEX_NAME = "[a-zA-Z]+";
  private static TreeMap<String, String> numberList = new TreeMap<>();
  private static Scanner scanner = new Scanner(System.in);
  private static String input = "";

  public static void main(String[] args) {
    numberList.put("Arbyz", "798172535");
    numberList.put("Barsik", "79817535");
    numberList.put("Zebra", "19426262");

    System.out.println("Вы можете: " +
        "\n-Добавить новый контакт, введя номер или имя контакта;" +
        "\n-Получить информацию о контакте, введя его номер или имя;" +
        "\n-Получить список контактов и номеров, введя команду LIST.");

    while (true) {
      input = scanner.nextLine();

      if (input.matches(REGEX_LIST)) {
        listPhoneBook(numberList);
        continue;
      } else if (input.matches(REGEX_NAME)) {
        addName(input);
      } else if (input.matches(REGEX_NUMBER)) {
        addNumber(input);
      } else {
        System.out.println("Комманда введена неправильно. Повторите ввод.");
      }
    }
  }

  private static void listPhoneBook(Map<String, String> map) {
    for (String name : map.keySet()) {
      System.out.println(name + " - " + map.get(name));
    }
  }

  private static void addNumber(String input) {
    if (numberInBook(input)) {
      System.out.println("Данный номер есть в телефонной книге.");
      printEntryByPhone(input);
      //searchAndPrintName(numberList);
    } else {
      System.out.println("Обнаружен новый номер, пожалуйста, укажите имя контакта:");
      String inputSecondPart = scanner.nextLine();
      while (!inputSecondPart.matches(REGEX_NAME)) {
        System.out.println("Имя указано неправильно, повторите ввод");
        inputSecondPart = scanner.nextLine();
      }
      numberList.put(inputSecondPart, input);
      System.out.println("Контакт успешно добавлен.");
    }
  }

  private static void addName(String input) {
    if (nameInBook(input)) {
      System.out.println("Данный контакт есть в телефонной книге.");
      System.out.println(input + " - " + numberList.get(input));
    } else {
      System.out.println("Обнаружен новый контакт, пожалуйста, добавьте номер:");
      String inputSecondPart = scanner.nextLine();
      while (!inputSecondPart.matches(REGEX_NUMBER)) {
        System.out.println("Номер указан неправильно, повторите ввод");
        inputSecondPart = scanner.nextLine();
      }
      numberList.put(input, inputSecondPart);
      System.out.println("Контакт успешно добавлен.");
    }
  }

  private static boolean nameInBook(String input) {
    return numberList.containsKey(input);
  }

  private static boolean numberInBook(String input) {
    return numberList.containsValue(input);
  }

  public static void searchAndPrintName(Map<String, String> map) {
    for (String name : map.keySet()) {
      if (input.equals(map.get(name))) {
        System.out.println(name + " - " + map.get(name));
      }
    }
  }

  private static void printEntryByPhone(String phone) {
    for (Entry<String, String> entry : numberList.entrySet()) {
      if (entry.getValue().equals(phone)) {
        System.out.println(entry.getKey() + " - " + entry.getValue());
      }
    }
  }
}