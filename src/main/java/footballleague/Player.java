package footballleague;

public class Player extends Person implements Playable {
    private String position;

    public Player(String name, String position) {
        super(name);
        this.position = position;
    }

    @Override
    public void playMatch() {
        // Логіка для участі в матчі
        System.out.println(name + " грає за команду як " + position);
    }

    @Override
    public String toString() {
        return name + " - " + position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
