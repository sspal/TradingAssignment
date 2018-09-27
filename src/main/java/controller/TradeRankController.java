package controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Constants;
import model.SettledTrade;
import model.custom.TradeAggregrates;
import service.ITradeConsolidationService;
import service.ITradeSettlementService;
import service.TradeConsolidationServiceImpl;
import service.TradeSettlementServiceImpl;

/**
 * TradeRankController Class
 * This class used as controller class
 * to get settled trades along-with consolidated.
 * 
 */
public class TradeRankController {
	 	
	/**
	 * This method applies settlement on trades.
	 * 
	 * @throws Exception 
	 */
		public void SettleTrades(List<SettledTrade> listTrades) throws Exception {
			settlementService = getTradeSettlementService();
			settlementService.applySettlement(listTrades);
		}
		/**
		 * This method gets settled trades.
		 * @param listTrades - list of trades
		 * @throws Exception 
		 */
		public List<SettledTrade> getSettledTrades() throws Exception {
			settlementService = getTradeSettlementService();
			return settlementService.getSettledTrades();
		}
		/**
		 * This method gets aggregated figures of trades date wise.
		 * 
		 * @return Map<Date, TradeAggregrates> mapAggregrate - maps of date, TradeAggregrates
		 * @throws Exception 
		 */
			public Map<Date, TradeAggregrates> getTradeAggregrateReport() throws Exception {
				service = getConsolidationService();
				return service.getTradeAggregate();
			}
			
			/**
			 * This method gets ranking figures of incoming trade entities.
			 * 
			 * @return Map<String, Double>- map of string, double
			 * @throws Exception 
			 */
				public Map<String, Double> getIncomingTradeRanking() throws Exception {
					service = getConsolidationService();
					return service.getEntityRanking(Constants.TRADE_SELL);
				}
	
				/**
				 * This method gets ranking figures of outgoing trade entities.
				 * 
				 * @return Map<String, Double>
				 * @throws Exception  -
				 */
					public Map<String, Double> getOutgoingTradeRanking() throws Exception {
						service = getConsolidationService();
						return service.getEntityRanking(Constants.TRADE_BUY);
					}
		/**
		 * ITradeConsolidationService reference.
		 */
		private ITradeConsolidationService service;
		/**
		 * ITradeConsolidationService instance generation if its null else returns existing instance.
		 */
		public ITradeConsolidationService getConsolidationService() {
		if(service == null) {
			service = new TradeConsolidationServiceImpl(); 
			return service;
		}
		else {
			return service;
		}
	}
		/**
		 * ITradeSettlementService reference.
		 */
		private ITradeSettlementService settlementService;
		/**
		 * ITradeSettlementService instance generation if its null else returns existing instance.
		 */
		public ITradeSettlementService getTradeSettlementService() {
		if(settlementService == null) {
			settlementService = new TradeSettlementServiceImpl(); 
			return settlementService;
		}
		else {
			return settlementService;
		}
	}

}
