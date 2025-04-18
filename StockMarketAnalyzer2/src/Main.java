import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String[] STOCK_NAMES = {"AAPL", "TSLA", "GOOG"};
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        StockMarketRegistry.registerMarket(market);

        // Add some stocks
        market.addStock("AAPL");
        market.addStock("GOOG");
        market.addStock("TSLA");

        // === Concurrent Trading ===
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
            String username = "User" + i;
            executor.submit(() -> runUser(username));
        }
    }

    private static void runUser(String username) {
        PortfolioListener user = new PortfolioListener(username);
        while (true) {
            try {
                boolean buy = RANDOM.nextBoolean();
                String stockName = STOCK_NAMES[RANDOM.nextInt(STOCK_NAMES.length)];
                int quantity = 1 + RANDOM.nextInt(5);

                if (buy) {
                    user.buyStock(stockName, quantity);
                } else {
                    user.sellStock(stockName, quantity);
                }

                Thread.sleep(500 + RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}