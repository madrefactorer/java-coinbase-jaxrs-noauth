public class RegisterToNew {

    public Coin newBCH(Buyer buyer, Coin newCoin, Coin originalCoin) {

        Coin[] coinArray = new Coin[1];
        String coinSymbol = newCoin.getCoinSymbol();

        if (coinSymbol.equals(Coin.BCH)) {
            coinArray[0] = newCoin;
            coinArray[0].registerSubscriber(buyer, false, false);
        } else {
            coinArray[0] = originalCoin;
        }
        return coinArray[0];
    }

    public Coin newBTC(Buyer buyer, Coin newCoin, Coin originalCoin) {

        Coin[] coinArray = new Coin[1];
        String coinSymbol = newCoin.getCoinSymbol();

        if (coinSymbol.equals(Coin.BTC)) {
            coinArray[0] = newCoin;
            coinArray[0].registerSubscriber(buyer, false, false);
        } else {
            coinArray[0] = originalCoin;
        }
        return coinArray[0];
    }

    public Coin newETH(Buyer buyer, Coin newCoin, Coin originalCoin) {

        Coin[] coinArray = new Coin[1];
        String coinSymbol = newCoin.getCoinSymbol();

        if (coinSymbol.equals(Coin.ETH)) {
            coinArray[0] = newCoin;
            coinArray[0].registerSubscriber(buyer, false, false);
        } else {
            coinArray[0] = originalCoin;
        }
        return coinArray[0];
    }

    public Coin newLTC(Buyer buyer, Coin newCoin, Coin originalCoin) {

        Coin[] coinArray = new Coin[1];
        String coinSymbol = newCoin.getCoinSymbol();

        if (coinSymbol.equals(Coin.LTC)) {
            coinArray[0] = newCoin;
            coinArray[0].registerSubscriber(buyer, false, false);
        } else {
            coinArray[0] = originalCoin;
        }
        return coinArray[0];
    }
}
