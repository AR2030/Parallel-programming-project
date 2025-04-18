import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

class StockMarket {
    private final ConcurrentHashMap<String, Stock> stocks = new ConcurrentHashMap<>();

    public void addStock(String name) {
        stocks.put(name, new Stock(name));
    }

    public Stock getStock(String name) {
        return stocks.get(name);
    }

    public Collection<Stock> getAllStocks() {
        return stocks.values();
    }
}