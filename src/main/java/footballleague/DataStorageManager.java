package footballleague;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class DataStorageManager {

    private Map<String, Team> teams;
    private List<Match> matches;

    public DataStorageManager() {
        this.teams = new HashMap<>();
        this.matches = new ArrayList<>();
    }

    // Додаємо тестові дані
    public void addSampleData() {
        Team team1 = new Team("Динамо", "Київ");
        Team team2 = new Team("Шахтар", "Донецьк");
        Team team3 = new Team("Карпати", "Львів");

        try {
            addTeam(team1);
            addTeam(team2);
            addTeam(team3);
        } catch (TeamAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        addMatch(new Match(team1, team2, 2, 1, LocalDate.of(2025, 4, 30)));
        addMatch(new Match(team2, team3, 0, 0, LocalDate.of(2025, 5, 1)));
    }

    // Додаємо команду
    public void addTeam(Team team) throws TeamAlreadyExistsException {
        if (teams.containsKey(team.getName())) {
            throw new TeamAlreadyExistsException("Команда з таким ім'ям вже існує!");
        }
        teams.put(team.getName(), team);
    }

    // Шукаємо команду за назвою
    public Team findTeamByName(String name) {
        return teams.get(name); // Тепер можемо швидко отримати команду за допомогою get
    }

    // Отримати всі команди
    public Collection<Team> getTeams() {
        return teams.values();  // Повертаємо всі значення з мапи
    }

    // Додаємо матч
    public void addMatch(Match match) {
        matches.add(match);

        // Оновлюємо очки командам
        if (match.getHomeScore() > match.getAwayScore()) {
            match.getHomeTeam().addPoints(3);
        } else if (match.getHomeScore() < match.getAwayScore()) {
            match.getAwayTeam().addPoints(3);
        } else {
            match.getHomeTeam().addPoints(1);
            match.getAwayTeam().addPoints(1);
        }
    }

    // Отримати всі матчі
    public List<Match> getMatches() {
        return matches;
    }

    // Використання Stream API для фільтрації команд
    public List<Team> getTeamsWithMoreThanNPoints(int minPoints) {
        return teams.values().stream()
                .filter(team -> team.getPoints() > minPoints)
                .collect(Collectors.toList());
    }

    // Використання лямбда-виразів для сортування команд
    public List<Team> getTeamsSortedByPoints() {
        return teams.values().stream()
                .sorted((team1, team2) -> Integer.compare(team2.getPoints(), team1.getPoints()))
                .collect(Collectors.toList());
    }

    // Підрахунок кількості матчів, де була нічия
    public long countDrawMatches() {
        return matches.stream()
                .filter(match -> match.getHomeScore() == match.getAwayScore())
                .count();
    }

    // Підрахунок середньої кількості очок команд
    public double getAveragePoints() {
        return teams.values().stream()
                .mapToInt(Team::getPoints)
                .average()
                .orElse(0.0);
    }
}
