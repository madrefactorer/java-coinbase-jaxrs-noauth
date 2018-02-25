import java.text.NumberFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.TimerTask;

public class RunPriceDownTicker extends TimerTask {

    private Coin bch;
    private Coin btc;
    private Coin eth;
    private Coin ltc;
    private double percent;

    RunPriceDownTicker(Coin bch, Coin btc, Coin eth, Coin ltc, double percent) {

        this.bch = bch;
        this.btc = btc;
        this.eth = eth;
        this.ltc = ltc;
        this.percent = percent;
    }

    public void run() {

        String ldt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //System.out.println("\nDownTicker date/time: " + ldt);
        CoinbaseJaxRS wsObj = new CoinbaseJaxRS();

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        for (String coin : Coin.coinSymbols) {

            double price = wsObj.retrievePrice(coin);

            switch(coin)
            {
                case Coin.BCH:
                    // If price has decreased by >= percentage amount since last .setPrice().
                    if ((bch.getPrice() - price) >= ((percent * bch.getPrice()) / 100)) {
                        bch.setPriceChangeAmt(formatter.format((price - bch.getPrice())));
                        bch.setPrice(price);
                        bch.setDateTime(ldt);
                        bch.setPriceChanged(true);
                    } else {
                        bch.setPriceChanged(false);
                    }
                        break;
                case Coin.BTC:
                    if ((btc.getPrice() - price) >= ((percent * btc.getPrice()) / 100)) {
                        btc.setPriceChangeAmt(formatter.format((price - btc.getPrice())));
                        btc.setPrice(price);
                        btc.setDateTime(ldt);
                        btc.setPriceChanged(true);
                    } else {
                        btc.setPriceChanged(false);
                    }
                    break;
                case Coin.ETH:
                    if ((eth.getPrice() - price) >= ((percent * eth.getPrice()) / 100)) {
                        eth.setPriceChangeAmt(formatter.format((price - eth.getPrice())));
                        eth.setPrice(price);
                        eth.setDateTime(ldt);
                        eth.setPriceChanged(true);
                    } else {
                        eth.setPriceChanged(false);
                    }
                    break;
                case Coin.LTC:
                    if ((ltc.getPrice() - price) >= ((percent * ltc.getPrice()) / 100)) {
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
