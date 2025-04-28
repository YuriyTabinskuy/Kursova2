package footballleague;

import java.util.ArrayList;
import java.util.List;

public class Team implements Scorable, Playable {
    private String name;
    private String city;
    private int points;
    private List<Player> players;

    public Team(String name, String city) {
        this.name = name;
        this.city = city;
        this.points = 0;
        this.players = new ArrayList<>();
    }

    @Override
    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void playMatch() {
        System.out.println(name + " грає матч!");
    }

    public Team(String name) {
        this(name, "Невідоме місто");
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public List<Player> getPlayers() { return players; }

    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public String toString() {
        return name + " (" + city + "), очки: " + points;
    }
}
