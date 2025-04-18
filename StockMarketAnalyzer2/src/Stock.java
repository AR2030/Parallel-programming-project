import java.util.concurrent.locks.ReentrantLock;

class Stock {
    private final String name;
    private int availableShares;
    private final ReentrantLock lock = new ReentrantLock();

    public Stock(String name) {
        this.name = name;
        this.availableShares = 10;
    }

    public String getName() {
        return name;
    }

    public int getAvailableShares() {
        lock.lock();
        try {
            return availableShares;
        } finally {
            lock.unlock();
        }
    }

    public boolean buySharesWithLogging(int shares, String username) {
        lock.lock();
        try {
            int before = availableShares;
            System.out.printf("[%s] 🛒 Attempting to BUY %d shares of %s | BEFORE: %d shares available%n",
                    username, shares, name, before);

            if (availableShares >= shares) {
                availableShares -= shares;
                System.out.printf("[%s] ✅ SUCCESS - Bought %d shares of %s | AFTER: %d shares available%n%n",
                        username, shares, name, availableShares);
                return true;
            } else {
                System.out.printf("[%s] ❌ FAILED - Not enough shares of %s | AFTER: %d shares available%n%n",
                        username, name, availableShares);
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean sellSharesWithLogging(int shares, String username) {
        lock.lock();
        try {
            int before = availableShares;
            System.out.printf("[%s] 📤 Attempting to SELL %d shares of %s | BEFORE: %d shares available%n",
                    username, shares, name, before);

            availableShares += shares;

            System.out.printf("[%s] ✅ SUCCESS - Sold %d shares of %s | AFTER: %d shares available%n%n",
                    username, shares, name, availableShares);
            return true;
        } finally {
            lock.unlock();
        }
    }
}
