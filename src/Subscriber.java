public interface Subscriber {

    void addToMapOfBuyersCoins(Coin coin, int i);
    void update(Coin coin, String priceChangeAmt, String dateTime);
}
