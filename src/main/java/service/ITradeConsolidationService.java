package service;
import java.util.List;

import model.SettledTrade;
import model.custom.EntityDetails;
import model.custom.TradeAggregrates;

/**
 TradeConsolidationService Interface 
 * This class used as main service class
 * to calculate Trade aggregrates and ranking.
 */
public interface ITradeConsolidationService {
	/**
	 * public getTradeAggregate() method for trade aggregrated figure calculation
	 * @param trades - List<SettledTrade>
	 * @return List<TradeAggregrates>
	 */
	List<TradeAggregrates> getTradeAggregate(List<SettledTrade> trades) throws Exception;
	/**
	 * public getEntityRanking() method for trade ranking figure calculation
	 * @param trades - List<SettledTrade>
	 * @return List<EntityDetails>
	 * @throws Exception 
	 */
	List<EntityDetails> getEntityRanking(List<SettledTrade> trades, String tradeType) throws Exception;
}
