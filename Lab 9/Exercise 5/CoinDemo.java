
import java.util.ArrayList;
import java.util.List;

// EXPLAIN: Each enum constant stores its value in cents.
enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private final int value;

    // EXPLAIN: This constructor assigns a value to each coin.
    Coin(int value) {
        this.value = value;
    }

    // EXPLAIN: Return the coin's value in cents.
    public int getValue() {
        return value;
    }
}

public class CoinDemo {

    // EXPLAIN: Return a list containing the fewest coins for an amount.
    public static List<Coin> fewestCoins(int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException(
                "Amount cannot be negative."
            );
        }

        List<Coin> result = new ArrayList<>();

        // EXPLAIN: Consider the coins from largest to smallest.
        Coin[] coins = {
            Coin.QUARTER,
            Coin.DIME,
            Coin.NICKEL,
            Coin.PENNY
        };

        // EXPLAIN: Use as many of each coin as possible.
        for (Coin coin : coins) {
            while (amount >= coin.getValue()) {
                result.add(coin);
                amount -= coin.getValue();
            }
        }

        return result;
    }

    public static void main(String[] args) {

        // EXPLAIN: The amount is expressed in cents.
        int amount = 87;

        List<Coin> coins = fewestCoins(amount);

        System.out.println("Amount: " + amount + " cents");
        System.out.println("Coins used: " + coins);
        System.out.println("Fewest number of coins: " + coins.size());
    }
}
