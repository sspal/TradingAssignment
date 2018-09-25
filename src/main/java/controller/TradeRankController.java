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
	 
	public TradeRankController() {
		service = getConsolidationService();
		settlementService = getTradeSettlementService();
	}
	
	/**
	 * This method applies settlement on trades.
	 * @param listTrades - list of trades
	 * @throws Exception 
	 */
		public void getSettledTrades(List<SettledTrade> listTrades) throws Exception {
			settlementService.applySettlement(listTrades);
		}
		
		/**
		 * This method gets aggregated figures of trades date wise.
		 * @param listTrades - list of trades
		 * @return List<TradeAggregrates> - list of TradeAggregrates
		 * @throws Exception 
		 */
			public List<TradeAggregrates> getTradeAggregrateReport(List<SettledTrade> listTrades) throws Exception {
				return service.getTradeAggregate(listTrades);
			}
			
			/**
			 * This method gets ranking figures of incoming trade entities.
			 * @param listTrades - list of trades
			 * @return List<EntityDetails>- list of EntityDetails
			 * @throws Exception 
			 */
				public List<EntityDetails> getIncomingTradeRanking(List<SettledTrade> listTrades) throws Exception {
					return service.getEntityRanking(listTrades, Constants.TRADE_SELL);
				}
	
				/**
				 * This method gets ranking figures of outgoing trade entities.
				 * @param listTrades - list of trades
				 * @return List<EntityDetails>- list of EntityDetails
				 * @throws Exception 
				 */
					public List<EntityDetails> getOutgoingTradeRanking(List<SettledTrade> listTrades) throws Exception {
						return service.getEntityRanking(listTrades, Constants.TRADE_BUY);
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
