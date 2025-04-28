package footballleague;

public class TeamAlreadyExistsException extends Exception {
    public TeamAlreadyExistsException(String message) {
        super(message);
    }
}
