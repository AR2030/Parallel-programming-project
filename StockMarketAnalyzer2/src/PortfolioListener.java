import java.util.HashMap;
import java.util.Map;

class PortfolioListener {
    private final String username;
    private final Map<String, Integer> portfolio = new HashMap<>();

    public PortfolioListener(String username) {
        this.username = username;
    }

    public synchronized void buyStock(String stockName, int shares) {
        Stock stock = StockMarketRegistry.getMarket().getStock(stockName);
        if (stock != null) {
            boolean success = stock.buySharesWithLogging(shares, username);
            if (success) {
                portfolio.put(stockName, portfolio.getOrDefault(stockName, 0) + shares);
            }
        }
    }

    public synchronized void sellStock(String stockName, int shares) {
        int currentShares = portfolio.getOrDefault(stockName, 0);
        Stock stock = StockMarketRegistry.getMarket().getStock(stockName);

        if (stock != null) {
            if (currentShares >= shares) {
                portfolio.put(stockName, currentShares - shares);
                if (portfolio.get(stockName) == 0) {
                    portfolio.remove(stockName);
                }
                stock.sellSharesWithLogging(shares, username);
            } else {
                System.out.printf("[%s] ❌ FAILED - Not enough shares to sell %d of %s | CURRENT OWNED: %d%n%n",
                        username, shares, stockName, currentShares);
            }
        }
    }
}