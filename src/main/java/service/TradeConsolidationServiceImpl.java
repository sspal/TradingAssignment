package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dao.ISettledTradesDao;
import dao.SettledTradesDaoImpl;
import model.Constants;
import model.SettledTrade;
import model.custom.EntityDetails;
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
	 * @param trades - List<SettledTrade>
	 * @return List<TradeAggregrates>
	 */
	public List<TradeAggregrates> getTradeAggregate() throws Exception {
		dao = getSettledTradesDao();
		List<SettledTrade> trades = dao.getSettledTrades();
		
		List<TradeAggregrates> aggregrateList = new ArrayList<TradeAggregrates>();
		Set<Date> setKey = new TreeSet<Date>();
		Iterator<SettledTrade> iterator = trades.iterator();
		SettledTrade trade;
		while (iterator.hasNext()) {
			trade = iterator.next();			
			setKey.add(trade.getSettleDate());
		}		
		getGrossValues(aggregrateList, trades, setKey);				
		return aggregrateList;
	}
	/**
	 * private getGrossValues() method for trade aggregrated figure calculation
	 * @param trades - List<SettledTrade>
	 * @param aggregrateList - List<TradeAggregrates>
	 * @param setKey - Set<String>
	 * @return void
	 */
	private void getGrossValues(List<TradeAggregrates> aggregrateList, List<SettledTrade> trades, Set<Date> setKey) {
		Iterator<SettledTrade> iterator;
		Iterator<Date> setIterator = setKey.iterator();
		SettledTrade trade;
		while (setIterator.hasNext()) {
			TradeAggregrates aggregrate = new TradeAggregrates();					
			aggregrate.setSettlementDate(setIterator.next());			
			aggregrateList.add(aggregrate);	
			iterator = trades.iterator();
			while(iterator.hasNext()) {				
			trade = iterator.next();
			if(aggregrate.getSettlementDate().equals(trade.getSettleDate())) 
			{
				if(trade.getTradeType().equals(Constants.TRADE_BUY))
					aggregrate.setBuyGrossValue(aggregrate.getBuyGrossValue() + trade.getTradeTotalValue());
				else
					aggregrate.setSellGrossValue(aggregrate.getSellGrossValue() + trade.getTradeTotalValue());							
			}			
		  }		
		}
	}
	/**
	 * public getEntityRanking() method for trade ranking figure calculation
	 * @param trades - List<SettledTrade>
	 * @return List<EntityDetails>
	 */
	public List<EntityDetails> getEntityRanking(String tradeType) throws Exception  {
		dao = getSettledTradesDao();
		List<SettledTrade> trades = dao.getSettledTrades();
		
		List<EntityDetails> entityList = new ArrayList<EntityDetails>();
		Set<String> setKey = new HashSet<String>();
		Iterator<SettledTrade> iterator = trades.iterator();
		SettledTrade trade;
		while (iterator.hasNext()) {
			trade = iterator.next();
			if(trade.getTradeType().equals(tradeType))
			setKey.add(trade.getTradeEntity());
		}		
		getEntityGross(entityList, trades, setKey, tradeType);
		RatingCompare ratingCompare = new RatingCompare(); 
		Collections.sort(entityList, ratingCompare);
		return entityList;
	}
	/**
	 * private getEntityGross() method for trade ranking figure calculation
	 * @param trades - List<SettledTrade>
	 * @param aggregrateList - List<TradeAggregrates>
	 * @param setKey - Set<String>
	 * @return void
	 */
	private void getEntityGross(List<EntityDetails> entityList, List<SettledTrade> trades, Set<String> setKey, String tradeType) {
		Iterator<SettledTrade> iterator;
		Iterator<String> setIterator = setKey.iterator();
		SettledTrade trade;
		while(setIterator.hasNext()) {
			EntityDetails entity = new EntityDetails();
			entity.setEntityName(setIterator.next());
			entityList.add(entity);
			iterator = trades.iterator();
			while(iterator.hasNext()) {				
			trade = iterator.next();
			if(entity.getEntityName().equals(trade.getTradeEntity()) && trade.getTradeType().equals(tradeType)) 
				entity.setTradeGrossValue(entity.getTradeGrossValue()+trade.getTradeTotalValue());
			}
		}		
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
