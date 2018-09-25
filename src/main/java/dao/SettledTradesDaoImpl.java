package dao;

import java.util.List;

import cache.InMemoryCache;
import model.SettledTrade;

public class SettledTradesDaoImpl implements ISettledTradesDao{	
	
	
	public void saveSettledTrades(List<SettledTrade> trades) throws Exception {
		InMemoryCache.getInstance().setTrades(trades);		
	}

	public List<SettledTrade> getSettledTrades() throws Exception {
		return InMemoryCache.getInstance().getTrades();
	}

}
