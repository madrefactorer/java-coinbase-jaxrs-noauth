import jersey.repackaged.com.google.common.collect.Multimap;
import jersey.repackaged.com.google.common.collect.Multimaps;

import java.util.*;

public class BuyersCoins {
    private static BuyersCoins ourInstance;

    Multimap<Buyer, Coin> multimapUp;
    Multimap<Buyer, Coin> multimapDown;
    List<Coin> listOfNewCoins;

    public static BuyersCoins getInstance() {
        if (ourInstance == null)
            ourInstance = new BuyersCoins();
        return ourInstance;
    }

    private BuyersCoins() {
        multimapUp = Multimaps.newListMultimap(new HashMap<>(), ArrayList::new);
        multimapDown = Multimaps.newListMultimap(new HashMap<>(), ArrayList::new);
        listOfNewCoins = new ArrayList<>();
    }

    public Multimap<Buyer, Coin> getMultimapUp() {
        return this.multimapUp;
    }

    public void putMultimapUp(Buyer buyer, Coin coin) {
        multimapUp.put(buyer, coin);
        listOfNewCoins.add(coin);
    }

    public Multimap<Buyer, Coin> getMultimapDown() {
        return this.multimapDown;
    }

    public void putMultimapDown(Buyer buyer, Coin coin) {
        multimapDown.put(buyer, coin);
        listOfNewCoins.add(coin);
    }

    public List<Coin> getListOfNewCoins() {
        return this.listOfNewCoins;
    }
}
