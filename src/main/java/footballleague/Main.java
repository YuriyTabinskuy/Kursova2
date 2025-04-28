package footballleague;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LeagueManager leagueManager = new LeagueManager(new DataStorageManager());

        // Додавання команди
        leagueManager.addTeam("Динамо", "Київ");
        leagueManager.addTeam("Шахтар", "Донецьк");

        // Додавання матчів
        leagueManager.addMatch("Динамо", "Шахтар", 2, 1);

        // Отримання і виведення команд
        System.out.println("Команди з більше ніж 10 очками:");
        leagueManager.getTeamsWithMoreThan10Points().forEach(team -> System.out.println(team.getName()));

        // Завершення багатопотокового виконання матчів
        leagueManager.getMatchExecutor().shutdown();
    }
}
