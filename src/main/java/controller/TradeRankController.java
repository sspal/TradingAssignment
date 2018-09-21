package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
 * This class used as main controller class
 * to input trade data list and get consolidated reports.
 * 
 */
public class TradeRankController {
	  /** 
	   * Getting Logging object to generate loggs.
	   */
	private	 static Logger log = Logger.getLogger(TradeRankController.class);
		public static void main(String args[]) {
			
			service = getConsolidationService();
			settlementService = getTradeSettlementService();
			
			ArrayList<SettledTrade> listTrades = createSampleTrades();				
			
			try {			
				
				settlementService.applySettlement(listTrades);
				
				generateInputReport(listTrades);
				
				List<TradeAggregrates> aggregrateList = service.getTradeAggregate(listTrades);
				
				generateAggregrateReport(aggregrateList);
				
				List<EntityDetails> buyEntities = service.getEntityRanking(listTrades, Constants.TRADE_BUY);
				
				generateBuyEntityReport(buyEntities);
				
				List<EntityDetails> sellEntities = service.getEntityRanking(listTrades, Constants.TRADE_SELL);			
			
				generateSellEntityReport(sellEntities);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * Method to create sample trade data.
		 * @return ArrayList<SettledTrade>
		 */
		public static ArrayList<SettledTrade> createSampleTrades() {
			ArrayList<SettledTrade> listTrades = new ArrayList<SettledTrade>();
			
			listTrades.add(new SettledTrade("Cipla", "21/Sep/2018", "21/Sep/2018", 
					"INR", 0.014f, 1000, Constants.TRADE_BUY, 95.01));
			listTrades.add(new SettledTrade("Axis", "21/Sep/2018", "21/Sep/2018", 
					"AED", 0.014f, 900, Constants.TRADE_BUY, 121.01));
			listTrades.add(new SettledTrade("ABC", "21/Sep/2018", "21/Sep/2018", 
					"SAR", 0.73f, 100, Constants.TRADE_BUY, 5.01));
			listTrades.add(new SettledTrade("Cipla", "21/Sep/2018", "23/Sep/2018", 
					"INR", 0.014f, 100, Constants.TRADE_BUY, 95.01));
			listTrades.add(new SettledTrade("Cipla", "21/Sep/2018", "21/Sep/2018", 
					"INR", 0.014f, 600, Constants.TRADE_SELL, 95.01));
			listTrades.add(new SettledTrade("Axis", "21/Sep/2018", "21/Sep/2018", 
					"AED", 0.014f, 100, Constants.TRADE_SELL, 121.01));
			listTrades.add(new SettledTrade("ABC", "21/Sep/2018", "21/Sep/2018", 
					"SAR", 0.73f, 500, Constants.TRADE_SELL, 5.01));
			listTrades.add(new SettledTrade("Cipla", "21/Sep/2018", "22/Sep/2018", 
					"INR", 0.014f, 200, Constants.TRADE_SELL, 95.01));
			
			return listTrades;
		}
		/**
		 * This method generates Incoming trades report.
		 * @param sellEntities - list of consolidated entities sell figure
		 */
		private static void generateSellEntityReport(List<EntityDetails> sellEntities) {
			int counter;
			log.info("                                                                                                ");
			log.info("                                                                                                ");
			log.info("Ranking Entities for Incoming based on Total Traded Amount in USD ");
			log.info("Rank   Entity-Name    Total-Amount-Incoming");
			log.info("-----------------------------------------------------------------------");
			for(int i=0; i<sellEntities.size(); i++) {
				counter = i;
				counter++;
				EntityDetails entitySell = sellEntities.get(i);
				log.info(counter+"        "+entitySell.getEntityName()+"             "+entitySell.getTradeGrossValue());
			}
			log.info("-----------------------------------------------------------------------");
		}
		/**
		 * This method generates Outgoing trades report.
		 * @param buyEntities - list of consolidated entities buy figure
		 */
		private static void generateBuyEntityReport(List<EntityDetails> buyEntities) {
			int counter;
			log.info("                                                                                                ");
			log.info("                                                                                                ");
			log.info("Ranking Entities for Outgoing based on Total Traded Amount in USD ");
			log.info("Rank   Entity-Name    Total-Amount-Outgoing");
			log.info("-----------------------------------------------------------------------");
			for(int i=0; i<buyEntities.size(); i++) {
				counter = i;
				counter++;
				EntityDetails entityBuy = buyEntities.get(i);
				log.info(counter+"        "+entityBuy.getEntityName()+"             "+entityBuy.getTradeGrossValue());
			}
			log.info("-----------------------------------------------------------------------");
		}
		/**
		 * This method generates report for everyday basis Gross Incoming/Outgoing figures.
		 * @param aggregrateList - list of consolidated trade Aggregrates
		 */
		private static void generateAggregrateReport(List<TradeAggregrates> aggregrateList) {
			log.info("                                                                                                ");
			log.info("                                                                                                ");
			log.info("Trade Amount Settled Everyday in USD Incoming/Outgoing ");
			log.info("Settlement-Date   Amount-Incoming    Amount-Outgoing");
			log.info("-----------------------------------------------------------------------");
			for(int i=0; i<aggregrateList.size(); i++) {
			 TradeAggregrates aggregrate = aggregrateList.get(i);
			 log.info(aggregrate.getSettlementDate()+"         "+aggregrate.getSellGrossValue()+"         "+aggregrate.getBuyGrossValue());
			}
			log.info("-----------------------------------------------------------------------");
		}
		/**
		 * This method generates report for all Input trade entities.
		 * @param listTrades - list of all trades
		 */
		private static void generateInputReport(ArrayList<SettledTrade> listTrades) {	
			log.info("Trade Figure Inputs after Applying Settlement ");
			log.info("Index Entity  Buy/Sell  Intended-SettlementDate  Actual-SettlementDate  Currency  Fx-Rate  Units  Price-per-unit  Trade-Gross ");
			log.info("--------------------------------------------------------------------------------------------------------------------------------");
				for(int i=0; i<listTrades.size(); i++) {
					SettledTrade trade = listTrades.get(i);
					log.info(i+"       "+trade.getTradeEntity()+"   "+trade.getTradeType()+"      "+trade.getIntendedSettlementDate()+"      "
					+trade.getSettleDate()+"       "+trade.getCurrency()+"        "+trade.getFxRate()+"       "+trade.getUnits()+"        "+trade.getUnitPrice()+"     "+trade.getTradeTotalValue());
				}
			log.info("--------------------------------------------------------------------------------------------------------------------------------");
		}
		
		/**
		 * ITradeConsolidationService reference.
		 */
		private static ITradeConsolidationService service;
		/**
		 * ITradeConsolidationService instance generation if its null else returns existing instance.
		 */
		public static ITradeConsolidationService getConsolidationService() {
		if(service == null) {
			service = new TradeConsolidationServiceImpl(); 
			return service;
		}
		else
			return service;
	}
		/**
		 * ITradeSettlementService reference.
		 */
		private static ITradeSettlementService settlementService;
		/**
		 * ITradeSettlementService instance generation if its null else returns existing instance.
		 */
		public static ITradeSettlementService getTradeSettlementService() {
		if(settlementService == null) {
			settlementService = new TradeSettlementServiceImpl(); 
			return settlementService;
		}
		else
			return settlementService;
	}

}
