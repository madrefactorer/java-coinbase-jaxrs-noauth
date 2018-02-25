import jersey.repackaged.com.google.common.collect.Multimap;

import java.text.NumberFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Map;

public class MainThread extends Thread {

    private Coin bch;
    private Coin btc;
    private Coin eth;
    private Coin ltc;

    MainThread(Coin bch, Coin btc, Coin eth, Coin ltc) {

        this.bch = bch;
        this.btc = btc;
        this.eth = eth;
        this.ltc = ltc;
    }
    /**
     Takes original four BCH/BTC/ETH/LTC coin objects set in Main for initial price comparisons to
     be used in the timer threads where price update notifications are then triggered based on the
     criteria set by each buyer here.
     Each buyer registration to a coin here creates 1-2 new coins if watchPriceUp/watchPriceDown
     registration parameter is set to true and runs 1-2 corresponding timer thread(s) in intervals
     set by each buyer, tracking and updating prices cumulatively as they move either up or down
     based on the buyer's price change percentage range.
     */
    public void run() {

        String ldt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("Start date/time: " + ldt);
        System.out.println("Initial prices:");
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        System.out.print(
                                 Coin.BCH + ":" + String.format("%13s", formatter.format(bch.getPrice()))
                        + "\n" + Coin.BTC + ":" + String.format("%13s", formatter.format(btc.getPrice()))
                        + "\n" + Coin.ETH + ":" + String.format("%13s", formatter.format(eth.getPrice()))
                        + "\n" + Coin.LTC + ":" + String.format("%13s", formatter.format(ltc.getPrice())) + "\n");


        // Set buyer preferences and register for subscriptions to BCH/BTC/ETH/LTC coin price updates.
        Buyer buyer1 = new Buyer();

        buyer1.setBuyerName("Code Master");
        buyer1.setPriceChangePercentage(.2); // Default minimum is set to 0.01%.
        buyer1.setTimerInSeconds(60); // Default minimum is set to 10.
        bch.registerSubscriber(buyer1, true, true); // Won't subscribe if both set to false.
        btc.registerSubscriber(buyer1, true, true);

        Buyer buyer2 = new Buyer();

        buyer2.setBuyerName("Noob");
        buyer2.setPriceChangePercentage(.4);
        buyer2.setTimerInSeconds(120);
        btc.registerSubscriber(buyer2, false, true);
        eth.registerSubscriber(buyer2, true, true);
        ltc.registerSubscriber(buyer2, true, true);


        java.util.Timer timer = new java.util.Timer();

        final Multimap<Buyer, Coin> multimapUp = BuyersCoins.getInstance().getMultimapUp();
        final Multimap<Buyer, Coin> multimapDown = BuyersCoins.getInstance().getMultimapDown();
        RegisterToNew registerToNew = new RegisterToNew();

        for (Map.Entry<Buyer, Coin> mapEntry : multimapUp.entries()) {

            Coin bchUp, btcUp, ethUp, ltcUp;
            Buyer buyer = mapEntry.getKey();
            Coin coin = mapEntry.getValue();

            bchUp = registerToNew.newBCH(buyer, coin, bch);
            btcUp = registerToNew.newBTC(buyer, coin, btc);
            ethUp = registerToNew.newETH(buyer, coin, eth);
            ltcUp = registerToNew.newLTC(buyer, coin, ltc);

            timer.schedule(new RunPriceUpTicker(bchUp, btcUp, ethUp, ltcUp,
                    buyer.getPriceChangePercentage()), 0, (buyer.getTimerInSeconds() * 1000));
        }

        for (Map.Entry<Buyer, Coin> mapEntry : multimapDown.entries()) {

            Coin bchDown, btcDown, ethDown, ltcDown;
            Buyer buyer = mapEntry.getKey();
            Coin coin = mapEntry.getValue();

            bchDown = registerToNew.newBCH(buyer, coin, bch);
            btcDown = registerToNew.newBTC(buyer, coin, btc);
            ethDown = registerToNew.newETH(buyer, coin, eth);
            ltcDown = registerToNew.newLTC(buyer, coin, ltc);

            timer.schedule(new RunPriceDownTicker(bchDown, btcDown, ethDown, ltcDown,
                    buyer.getPriceChangePercentage()), 0, (buyer.getTimerInSeconds() * 1000));
        }
    }
}
