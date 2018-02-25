public class Main {

    public static void main(String[] args) {

        Coin btc = new Coin();
        Coin ltc = new Coin();
        Coin eth = new Coin();
        Coin bch = new Coin();

        CoinbaseJaxRS wsObj = new CoinbaseJaxRS();

        // Initialize Coin objects.
        for (String coin : Coin.coinSymbols) {

            double price = wsObj.retrievePrice(coin);

            switch(coin)
            {
                case Coin.BCH:
                    bch.setPrice(price);
                    bch.setCoinSymbol(coin);
                    //BuyersCoins.getInstance().addToArrayOfOriginalCoins(bch, 0);
                    break;
                case Coin.BTC:
                    btc.setPrice(price);
                    btc.setCoinSymbol(coin);
                    //BuyersCoins.getInstance().addToArrayOfOriginalCoins(btc, 1);
                    break;
                case Coin.ETH:
                    eth.setPrice(price);
                    eth.setCoinSymbol(coin);
                    //BuyersCoins.getInstance().addToArrayOfOriginalCoins(eth, 2);
                    break;
                case Coin.LTC:
                    ltc.setPrice(price);
                    ltc.setCoinSymbol(coin);
                    //BuyersCoins.getInstance().addToArrayOfOriginalCoins(ltc, 3);
                    break;
            }
        }

        // Send coin objects with initial symbols and prices to main thread to add subscribers.
        MainThread tThread = new MainThread(bch, btc, eth, ltc);
        tThread.start();
    }
}
