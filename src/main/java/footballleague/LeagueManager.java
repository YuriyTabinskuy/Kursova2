package footballleague;

import java.time.LocalDate;
import java.util.List;

public class LeagueManager {
    private DataStorageManager dataStorageManager;
    private MatchExecutor matchExecutor;

    public LeagueManager(DataStorageManager dataStorageManager) {
        this.dataStorageManager = dataStorageManager;
        this.matchExecutor = new MatchExecutor();
    }

    public void addSampleData() {
        dataStorageManager.addSampleData();
    }

    public void addTeam(String name, String city) {
        Team team = new Team(name, city);
        try {
            dataStorageManager.addTeam(team);
        } catch (TeamAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Team> getTeams() {
        return (List<Team>) dataStorageManager.getTeams();
    }

    public boolean addMatch(String homeName, String awayName, int homeScore, int awayScore) {
        Team homeTeam = dataStorageManager.findTeamByName(homeName);
        Team awayTeam = dataStorageManager.findTeamByName(awayName);

        if (homeTeam == null || awayTeam == null) {
            return false;
        }

        Match match = new Match(homeTeam, awayTeam, homeScore, awayScore, LocalDate.now());
        dataStorageManager.addMatch(match);
        matchExecutor.executeMatch(match);  // Виконання матчу в окремому потоці
        return true;
    }

    public List<Match> getMatches() {
        return dataStorageManager.getMatches();
    }

    // Використання Stream API для отримання команд з більше ніж 10 очками
    public List<Team> getTeamsWithMoreThan10Points() {
        return dataStorageManager.getTeamsWithMoreThanNPoints(10);
    }

    // Публічний метод для доступу до matchExecutor
    public MatchExecutor getMatchExecutor() {
        return matchExecutor;
    }
}
