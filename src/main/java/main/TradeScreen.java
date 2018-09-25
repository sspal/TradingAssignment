package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import controller.TradeRankController;
import model.Constants;
import model.SettledTrade;
import model.custom.EntityDetails;
import model.custom.TradeAggregrates;

/**
 * TradeScreen Class
 * This class used as main controller class
 * to input trade data list and get consolidated reports.
 * 
 */
public class TradeScreen {
	/** 
	   * Getting Logging object to generate loggs.
	   */
	private	 static Logger log = Logger.getLogger(TradeScreen.class);
	public static void main(String args[]) {		
		TradeRankController controller = new TradeRankController();
		
		try {			
			ArrayList<SettledTrade> listTrades = createSampleTrades();				
			
			controller.SettleTrades(listTrades);
			
			List<SettledTrade> settledTrades = controller.getSettledTrades();
			
			generateInputReport(settledTrades);
			
			List<TradeAggregrates> aggregrateList = controller.getTradeAggregrateReport();
			
			generateAggregrateReport(aggregrateList);
			
			List<EntityDetails> buyEntities = controller.getOutgoingTradeRanking();
			
			generateBuyEntityReport(buyEntities);
			
			List<EntityDetails> sellEntities = controller.getIncomingTradeRanking();			
		
			generateSellEntityReport(sellEntities);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Method to create sample trade data.
	 * @return ArrayList<SettledTrade>
	 * @throws ParseException 
	 */
	public static ArrayList<SettledTrade> createSampleTrades() throws ParseException {
		ArrayList<SettledTrade> listTrades = new ArrayList<SettledTrade>();
		
		listTrades.add(new SettledTrade("Cipla", getDate("21/Sep/2018"), getDate("21/Sep/2018"), 
				"INR", 0.014f, 1000, Constants.TRADE_BUY, 95.01));
		listTrades.add(new SettledTrade("Axis", getDate("21/Sep/2018"), getDate("21/Sep/2018"), 
				"AED", 0.014f, 900, Constants.TRADE_BUY, 121.01));
		listTrades.add(new SettledTrade("ABC", getDate("21/Sep/2018"), getDate("21/Sep/2018"), 
				"SAR", 0.73f, 100, Constants.TRADE_BUY, 5.01));
		listTrades.add(new SettledTrade("Cipla", getDate("21/Sep/2018"), getDate("23/Sep/2018"), 
				"INR", 0.014f, 100, Constants.TRADE_BUY, 95.01));
		listTrades.add(new SettledTrade("Cipla", getDate("21/Sep/2018"), getDate("21/Sep/2018"), 
				"INR", 0.014f, 600, Constants.TRADE_SELL, 95.01));
		listTrades.add(new SettledTrade("Axis", getDate("21/Sep/2018"), getDate("21/Sep/2018"), 
				"AED", 0.014f, 100, Constants.TRADE_SELL, 121.01));
		listTrades.add(new SettledTrade("ABC", getDate("21/Sep/2018"), getDate("21/Sep/2018"), 
				"SAR", 0.73f, 500, Constants.TRADE_SELL, 5.01));
		listTrades.add(new SettledTrade("Cipla", getDate("21/Sep/2018"), getDate("22/Sep/2018"), 
				"INR", 0.014f, 200, Constants.TRADE_SELL, 95.01));
		
		return listTrades;
	}
	private static Date getDate(String date) throws ParseException {		
		return format.parse(date);
	}
	/**
	 * Simpledateformat instance
	 */
	
	private static SimpleDateFormat format = new SimpleDateFormat(Constants.Date_Pattern);
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
	private static void generateInputReport(List<SettledTrade> listTrades) {	
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
	
}
