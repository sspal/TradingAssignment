package cache;

import java.util.List;

import model.SettledTrade;

public class InMemoryCache {
	private static InMemoryCache instance;
	 private InMemoryCache() {
	    }
	private List<SettledTrade> trades;
	
	public void setTrades(List<SettledTrade> trades) {
		instance.trades = trades;
	}
	public List<SettledTrade> getTrades() {
		return instance.trades;
	}
	public static InMemoryCache getInstance() {
        if (instance == null) {
            synchronized (InMemoryCache.class) {
                if (instance == null) {
                    instance = new InMemoryCache();
                }
            }
        }
        return instance;
    }
}
