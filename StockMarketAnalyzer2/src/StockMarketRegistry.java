class StockMarketRegistry {
    private static StockMarket market;

    public static void registerMarket(StockMarket m) {
        market = m;
    }

    public static StockMarket getMarket() {
        return market;
    }
}
