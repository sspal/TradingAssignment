package service;
import java.util.List;

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
	 * 
	 * @return List<TradeAggregrates>
	 */
	List<TradeAggregrates> getTradeAggregate() throws Exception;
	/**
	 * public getEntityRanking() method for trade ranking figure calculation
	 * @param String - tradeType
	 * @return List<EntityDetails>
	 * @throws Exception 
	 */
	List<EntityDetails> getEntityRanking(String tradeType) throws Exception;
}
