import java.text.NumberFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.TimerTask;

public class RunPriceUpTicker extends TimerTask {

    private Coin bch;
    private Coin btc;
    private Coin eth;
    private Coin ltc;
    private double percent;

    RunPriceUpTicker(Coin bch, Coin btc, Coin eth, Coin ltc, double percent) {

        this.bch = bch;
        this.btc = btc;
        this.eth = eth;
        this.ltc = ltc;
        this.percent = percent;
    }

    public void run() {


        String ldt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //System.out.println("\nUpTicker date/time: " + ldt);

        CoinbaseJaxRS wsObj = new CoinbaseJaxRS();

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        for (String coin : Coin.coinSymbols) {

            double price = wsObj.retrievePrice(coin);

            switch(coin)
            {
                case Coin.BCH:
                    // If price has increased >= percentage amount since last .setPrice().
                    if ((price - bch.getPrice()) >= ((percent * bch.getPrice()) / 100)) {
                        // Set changed amount...
                        bch.setPriceChangeAmt(formatter.format((price - bch.getPrice())));
                        // ...then set to new price...
                        bch.setPrice(price);
                        // ...set ticker date/time...
                        bch.setDateTime(ldt);
                        // ...last set price changed to true to trigger update.
                        bch.setPriceChanged(true);
                    } else {
                        bch.setPriceChanged(false);
                    }
                        break;
                case Coin.BTC:
                    if ((price - btc.getPrice()) >= ((percent * btc.getPrice()) / 100)) {
                        btc.setPriceChangeAmt(formatter.format((price - btc.getPrice())));
                        btc.setPrice(price);
                        btc.setDateTime(ldt);
                        btc.setPriceChanged(true);
                    } else {
                        btc.setPriceChanged(false);
                    }
                    break;
                case Coin.ETH:
                    if ((price - eth.getPrice()) >= ((percent * eth.getPrice()) / 100)) {
                        eth.setPriceChangeAmt(formatter.format((price - eth.getPrice())));
                        eth.setPrice(price);
                        eth.setDateTime(ldt);
                        eth.setPriceChanged(true);
                    } else {
                        eth.setPriceChanged(false);
                    }
                    break;
                case Coin.LTC:
                    if ((price - ltc.getPrice()) >= ((percent * ltc.getPrice()) / 100)) {
                        ltc.setPriceChangeAmt(formatter.format((price - ltc.getPrice())));
                        ltc.setPrice(price);
                        ltc.setDateTime(ldt);
                        ltc.setPriceChanged(true);
                    } else {
                        ltc.setPriceChanged(false);
                    }
                    break;
            }
        }
    }
}
