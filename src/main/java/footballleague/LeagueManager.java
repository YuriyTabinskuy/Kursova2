package footballleague;

import java.util.List;
import java.util.ArrayList;  // Додаємо імпорт для ArrayList
import java.time.LocalDate;  // Додаємо імпорт для LocalDate
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LeagueManager {
    private DataStorageManager dataStorageManager;
    private ExecutorService matchExecutor;

    public LeagueManager(DataStorageManager dataStorageManager) {
        this.dataStorageManager = dataStorageManager;
        this.matchExecutor = Executors.newFixedThreadPool(2);  // Використовуємо пул потоків для виконання матчів
    }

    public void addSampleData() {
        dataStorageManager.addSampleData();
    }

    public void addTeam(String name, String city) {
        Team team = new Team(name, city);
        try {
            dataStorageManager.addTeam(team);
        } catch (TeamAlreadyExistsException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public List<Team> getTeams() {
        return new ArrayList<>(dataStorageManager.getTeams());  // Перетворюємо Collection в List
    }

    public boolean addMatch(String homeName, String awayName, int homeScore, int awayScore) {
        Team homeTeam = dataStorageManager.findTeamByName(homeName);
        Team awayTeam = dataStorageManager.findTeamByName(awayName);

        if (homeTeam == null || awayTeam == null) {
            return false;
        }

        Match match = new Match(homeTeam, awayTeam, homeScore, awayScore, LocalDate.now());  // Додаємо поточну дату як параметр
        matchExecutor.submit(() -> dataStorageManager.addMatch(match));  // Виконання матчу в окремому потоці
        return true;
    }

    public List<Match> getMatches() {
        return dataStorageManager.getMatches();
    }

    public void shutdown() {
        matchExecutor.shutdown();  // Завершуємо потоки після виконання
    }
}
