import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CoinSet implements ISet<CoinSet.CoinCountSet> {
    public static enum Coin {
        QUARTER(25),
        DIMES(10),
        NICKELS(5),
        PENNIES(1);
        private final int valueCents;

        Coin(int value) {
            this.valueCents = value;
        }

        public int getValueCents() {
            return this.valueCents;
        }
    }
    //ORDER: QUARTER, DIMES,NICKELS,PENNIES

    public static class CoinCountSet {
        private int[] coinCount = new int[]{0, 0, 0, 0};

        public int[] getCoinCount() {
            return this.coinCount;
        }

        public void increaseCoin(Coin coin) {
            switch (coin) {
                case QUARTER -> this.coinCount[0] += 1;
                case DIMES -> this.coinCount[1] += 1;
                case NICKELS -> this.coinCount[2] += 1;
                case PENNIES -> this.coinCount[3] += 1;
            }
        }
        public void addCoin(Coin coin,int ammount) {
            switch (coin) {
                case QUARTER -> this.coinCount[0] += 1;
                case DIMES -> this.coinCount[1] += 1;
                case NICKELS -> this.coinCount[2] += 1;
                case PENNIES -> this.coinCount[3] += 1;
            }
        }
        public String countToString(){
            return Arrays.toString(this.getCoinCount());
        }
    }

    private ArrayList<CoinCountSet> coinCountSets;


    @Override
    public boolean add(CoinCountSet e) {
        int index = e.countToString().hashCode();
        if(coinCountSets.get(index) == null){
            coinCountSets.add(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(ISet<CoinCountSet> s) {
        return false;
    }

    @Override
    public boolean contains(CoinCountSet e) {
        int index = e.countToString().hashCode();
        return this.coinCountSets.get(index) != null;
    }

    @Override
    public boolean equals(ISet s) {
        return s.equals(this);
    }

    @Override
    public Iterator<CoinCountSet> iterator() {
        return this.coinCountSets.iterator();
    }

    //Unallowed remove counts sets
    @Override
    public boolean remove(CoinCountSet e) {
        return false;
    }

    @Override
    public int size() {
        return this.coinCountSets.size();
    }

    @Override
    public CoinCountSet[] toArray() {
        return new CoinCountSet[0];
    }

    public static CoinSet makeChange(int n) {
        CoinSet coinSet = new CoinSet();
        //For each index from coinIndex, the size
        makeChangeRecursive(n, 0, new CoinCountSet(), coinSet);
        return coinSet;
    }

    private static void makeChangeRecursive(int numberRemaining, int coinIndex, CoinCountSet currentSet, CoinSet result) {
        if (numberRemaining == 0) {
            result.add(currentSet);
            return;
        }

        if (numberRemaining < 0 || coinIndex >= Coin.values().length) {
            return;
        }
        Coin currentCoin = Coin.values()[coinIndex];
        CoinCountSet coinCountSet = new CoinCountSet();
        for (int i = 0; i < currentSet.getCoinCount().length; i++) {
            coinCountSet.getCoinCount()[i] = currentSet.getCoinCount()[i];
        }
        //Still calculating with others quarter while there are remainder
        while (numberRemaining >= 0) {
            makeChangeRecursive(numberRemaining, coinIndex + 1, coinCountSet, result);
            if (numberRemaining >= currentCoin.getValueCents()) {
                numberRemaining -= currentCoin.getValueCents();
                coinCountSet.increaseCoin(currentCoin);
            } else {
                break;
            }
        }
    }


}