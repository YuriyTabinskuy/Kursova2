package footballleague;

import java.time.LocalDate;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    private LocalDate matchDate;

    public Match(Team homeTeam, Team awayTeam, int homeScore, int awayScore, LocalDate matchDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.matchDate = matchDate;
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

    public LocalDate getMatchDate() {
        return matchDate;
    }
}
