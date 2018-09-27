package service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dao.ISettledTradesDao;
import dao.SettledTradesDaoImpl;
import model.Constants;
import model.SettledTrade;
import model.custom.TradeAggregrates;
/**
 * TradeConsolidationServiceImpl Class
 * This class used as main service class
 * to calculate Trade aggregrates and ranking.
 * 
 */
public class TradeConsolidationServiceImpl implements ITradeConsolidationService {	
	
	/**
	 * public getTradeAggregate() method for trade aggregrated figure calculation
	 * 
	 * @return Map<Date, TradeAggregrates>
	 */
	public Map<Date, TradeAggregrates> getTradeAggregate() throws Exception {
		dao = getSettledTradesDao();
		List<SettledTrade> trades = dao.getSettledTrades();		
		Map<Date, TradeAggregrates> mapAggregrate = new TreeMap<Date, TradeAggregrates>();
		TradeAggregrates aggregrate = null;		
		for(SettledTrade trade : trades) {
			aggregrate = mapAggregrate.get(trade.getSettleDate());			
			if(aggregrate == null) {
				aggregrate = new TradeAggregrates();
			}
			
			if(trade.getTradeType().equals(Constants.TRADE_BUY))
				aggregrate.setBuyGrossValue(aggregrate.getBuyGrossValue() + trade.getTradeTotalValue());
			else
				aggregrate.setSellGrossValue(aggregrate.getSellGrossValue() + trade.getTradeTotalValue());
			
			mapAggregrate.put(trade.getSettleDate(), aggregrate);
		}					
		return mapAggregrate;
		}
		
		
	
	/**
	 * public getEntityRanking() method for trade ranking figure calculation
	 * @param trades - List<SettledTrade>
	 * @return Map<String, Double>
	 */
	public Map<String, Double> getEntityRanking(String tradeType) throws Exception  {
		dao = getSettledTradesDao();
		List<SettledTrade> trades = dao.getSettledTrades();
		
		HashMap<String, Double> entityMap = new HashMap<String, Double>();
		Double value = 0.0;
		for(SettledTrade trade : trades) {
			if(trade.getTradeType().equals(tradeType)) {
			value = entityMap.get(trade.getTradeEntity());
			value = (value != null) ? value : 0.0;
			entityMap.put(trade.getTradeEntity(), (value + trade.getTradeTotalValue()));
			}
		}
		
		RatingCompare comp = new RatingCompare(entityMap);
		Map<String, Double> sortedEntities = new TreeMap<String, Double>(comp);
		sortedEntities.putAll(entityMap);
		entityMap = null;
		return sortedEntities;
	}
	
	/**
	 * ISettledTradesDao reference.
	 */
	private ISettledTradesDao dao;
	/**
	 * ISettledTradesDao instance generation if its null else returns existing instance.
	 */
	public ISettledTradesDao getSettledTradesDao() {
	if(dao == null) {
		dao = new SettledTradesDaoImpl(); 
		return dao;
	}
	else {
		return dao;
	}
}

}
