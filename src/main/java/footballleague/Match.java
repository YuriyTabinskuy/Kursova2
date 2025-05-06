package footballleague;

import java.time.LocalDate;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    private LocalDate date;

    public Match(Team homeTeam, Team awayTeam, int homeScore, int awayScore, LocalDate date) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.date = date;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) - (%d) %s | Дата: %s",
                homeTeam.getName(), homeScore, awayScore, awayTeam.getName(), date);
    }
}
