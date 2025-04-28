package footballleague;

public class Team {
    private String name;
    private String city;
    private int points;

    public Team(String name, String city) {
        this.name = name;
        this.city = city;
        this.points = 0;  // Початкове значення очок
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;  // Метод для встановлення очок
    }

    @Override
    public String toString() {
        return "Команда: " + name + ", Місто: " + city + ", Очки: " + points;
    }
}
