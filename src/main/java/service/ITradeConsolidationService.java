package service;
import java.util.Date;
import java.util.Map;

import model.custom.TradeAggregrates;

/**
 TradeConsolidationService Interface 
 * This class used as main service class
 * to calculate Trade aggregates and ranking.
 */
public interface ITradeConsolidationService {
	/**
	 * public getTradeAggregate() method for trade aggregrated figure calculation
	 * 
	 * @return Map<Date, TradeAggregrates>
	 */
	Map<Date, TradeAggregrates> getTradeAggregate() throws Exception;
	/**
	 * public getEntityRanking() method for trade ranking figure calculation
	 * @param String - tradeType
	 * @return Map<String, Double>
	 * @throws Exception 
	 */
	Map<String, Double> getEntityRanking(String tradeType) throws Exception;
}
