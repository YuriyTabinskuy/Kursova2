package footballleague;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static LeagueManager leagueManager;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Ініціалізація менеджера ліги і сканера
        DataStorageManager dataStorageManager = new DataStorageManager();
        leagueManager = new LeagueManager(dataStorageManager);
        scanner = new Scanner(System.in);

        // Додати стартові дані
        leagueManager.addSampleData();

        // Головне меню
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> addTeam();
                case 2 -> listTeams();
                case 3 -> addMatch();
                case 4 -> listMatches();
                case 5 -> exit = true;
                default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }

        System.out.println("Дякую за використання системи управління футбольною лігою!");
    }

    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Додати команду");
        System.out.println("2. Переглянути всі команди");
        System.out.println("3. Додати матч");
        System.out.println("4. Переглянути всі матчі");
        System.out.println("5. Вийти");
        System.out.print("Ваш вибір: ");
    }

    private static void addTeam() {
        System.out.print("Введіть назву команди: ");
        String name = scanner.nextLine();
        System.out.print("Введіть місто команди: ");
        String city = scanner.nextLine();

        leagueManager.addTeam(name, city);
        System.out.println("Команду додано успішно!");
    }

    private static void listTeams() {
        List<Team> teams = new ArrayList<>(leagueManager.getTeams());  // Використовуємо ArrayList для перетворення Collection в List
        if (teams.isEmpty()) {
            System.out.println("Немає команд для відображення.");
        } else {
            System.out.println("\nСписок команд:");
            for (Team team : teams) {
                System.out.println(team);
            }
        }
    }

    private static void addMatch() {
        System.out.print("Введіть назву домашньої команди: ");
        String homeName = scanner.nextLine();
        System.out.print("Введіть назву виїзної команди: ");
        String awayName = scanner.nextLine();
        System.out.print("Введіть кількість забитих голів домашньої команди: ");
        int homeScore = readInt();
        System.out.print("Введіть кількість забитих голів виїзної команди: ");
        int awayScore = readInt();

        boolean success = leagueManager.addMatch(homeName, awayName, homeScore, awayScore);
        if (success) {
            System.out.println("Матч додано успішно!");
        } else {
            System.out.println("Помилка при додаванні матчу. Перевірте назви команд.");
        }
    }

    private static void listMatches() {
        List<Match> matches = leagueManager.getMatches();
        if (matches.isEmpty()) {
            System.out.println("Немає матчів для відображення.");
        } else {
            System.out.println("\nСписок матчів:");
            for (Match match : matches) {
                System.out.println(match);
            }
        }
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Введіть коректне число!");
            scanner.next(); // Пропустити некоректне введення
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Прочитати пропущений \n після числа
        return value;
    }
}
