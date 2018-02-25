import java.util.*;

public class Coin implements Subject {

    private String coinSymbol;
    private double price;
    private String priceChangeAmt;
    private String priceDir;
    private List<Subscriber> listOfSubscribers = new ArrayList<>();
    private String dateTime;
    public static final String BCH = "BCH";
    public static final String BTC = "BTC";
    public static final String ETH = "ETH";
    public static final String LTC = "LTC";
    public static final String[] coinSymbols = { BCH, BTC, ETH, LTC };
    public static final Map<String,String> URLHASH;
    static{
        Hashtable<String,String> tmp = new Hashtable<>();

        tmp.put(BCH, "https://api.coinbase.com/v2/prices/BCH-USD/buy");
        tmp.put(BTC, "https://api.coinbase.com/v2/prices/BTC-USD/buy");
        tmp.put(ETH, "https://api.coinbase.com/v2/prices/ETH-USD/buy");
        tmp.put(LTC, "https://api.coinbase.com/v2/prices/LTC-USD/buy");

        URLHASH = Collections.unmodifiableMap(tmp);
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) { this.coinSymbol = coinSymbol; }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceChanged(boolean priceChanged) {
        //this.priceChanged = priceChanged;
        if(priceChanged) {
            notifySubscribers();
        }
    }

    public void setPriceChangeAmt(String priceChangeAmt) {
        this.priceChangeAmt = priceChangeAmt;
    }

    private void setPriceDir(String priceDir) {
        this.priceDir = priceDir;
    }

    public String getPriceDir() {
        return priceDir;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public void registerSubscriber(Subscriber subscriber, boolean watchPriceUp, boolean watchPriceDown) {

        Coin newCoin;
        boolean isNew = (BuyersCoins.getInstance().getListOfNewCoins().contains(this));

        if (isNew) {
            listOfSubscribers.add(subscriber);
        } else {
            if (watchPriceUp) {
                newCoin = createNewCoinObj(this);
                newCoin.setPriceDir("increased");
                subscriber.addToMapOfBuyersCoins(newCoin, 1);
            }
            if (watchPriceDown) {
                newCoin = createNewCoinObj(this);
                newCoin.setPriceDir("decreased");
                subscriber.addToMapOfBuyersCoins(newCoin, 0);
            }
        }
    }

    /*
    @Override
    public void removeSubscriber(Subscriber subscriber) {

        listOfSubscribers.remove(subscriber);
    }
    */

    private Coin createNewCoinObj(Coin coin) {

            Coin[] coinArray = new Coin[1];
            coinArray[0] = new Coin();
            coinArray[0].setCoinSymbol(coin.getCoinSymbol());
            coinArray[0].setPrice(coin.getPrice());
            return coinArray[0];
    }


    @Override
    public void notifySubscribers() {

        for(Subscriber subscriber : listOfSubscribers)
        {
            subscriber.update(this, priceChangeAmt, dateTime);
        }
    }

    //Constructor
    public Coin() {

    }
}
