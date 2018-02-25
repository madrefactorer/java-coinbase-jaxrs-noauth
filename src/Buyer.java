import java.text.NumberFormat;

public class Buyer implements Subscriber {

    private String buyerName;
    //private String buyerEmail; // Add email notify later.
    private double priceChangePercentage;
    private int timerInSeconds;

    private String getBuyerName() {

        if (buyerName == null) {
            this.buyerName = "?";
        }
        return buyerName;
    }

    public void setBuyerName(String newBuyerName) {

        if (newBuyerName.isEmpty()) {
            this.buyerName = "?";
        } else {
            this.buyerName = newBuyerName;
        }
    }

    public double getPriceChangePercentage() {

        if (priceChangePercentage < .01) {
            priceChangePercentage = .01;
        }
        return priceChangePercentage;
    }

    public void setPriceChangePercentage(double yourPriceChangeRangePercentage) {

            this.priceChangePercentage = yourPriceChangeRangePercentage;
    }

    public int getTimerInSeconds() {

        if (timerInSeconds < 10) {
            timerInSeconds = 10;
        }
        return timerInSeconds;
    }

    public void setTimerInSeconds(int timerInSeconds) {

        this.timerInSeconds = timerInSeconds;
    }


    @Override
    public void addToMapOfBuyersCoins(Coin coin, int i) {
        if (i == 1) {
            BuyersCoins.getInstance().putMultimapUp(this, coin);
        } else {
            BuyersCoins.getInstance().putMultimapDown(this, coin);
        }
    }

    @Override
    // Called if Coin price changed by set amount is true. Gives an update to subscribers.
     public void update(Coin coin, String priceChangeAmt, String dateTime) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        String msg = (coin.getPrice() == -1) ? "Price not found." : "\n" + dateTime + "\nDear "
                + this.getBuyerName() + " the price of " + coin.getCoinSymbol() + " has " + coin.getPriceDir()
                + " by " + priceChangeAmt + " to: " + formatter.format(coin.getPrice());

        System.out.println(msg);
    }
}
