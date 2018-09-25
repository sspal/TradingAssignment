package controller;

import java.util.List;

import model.Constants;
import model.SettledTrade;
import model.custom.EntityDetails;
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
		 * @return List<TradeAggregrates> - list of TradeAggregrates
		 * @throws Exception 
		 */
			public List<TradeAggregrates> getTradeAggregrateReport() throws Exception {
				service = getConsolidationService();
				return service.getTradeAggregate();
			}
			
			/**
			 * This method gets ranking figures of incoming trade entities.
			 * 
			 * @return List<EntityDetails>- list of EntityDetails
			 * @throws Exception 
			 */
				public List<EntityDetails> getIncomingTradeRanking() throws Exception {
					service = getConsolidationService();
					return service.getEntityRanking(Constants.TRADE_SELL);
				}
	
				/**
				 * This method gets ranking figures of outgoing trade entities.
				 * 
				 * @return List<EntityDetails>- list of EntityDetails
				 * @throws Exception 
				 */
					public List<EntityDetails> getOutgoingTradeRanking() throws Exception {
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
