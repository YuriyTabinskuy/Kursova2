package footballleague;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatchExecutor {
    private ExecutorService executor;

    public MatchExecutor() {
        this.executor = Executors.newFixedThreadPool(4); // Чотири потоки
    }

    public void executeMatch(Match match) {
        executor.submit(() -> {
            // Логіка для виконання матчу
            System.out.println("Виконання матчу: " + match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName());
            // Симуляція результату матчу
            try {
                Thread.sleep(2000); // Імітуємо затримку
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Матч завершено: " + match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName());
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}
