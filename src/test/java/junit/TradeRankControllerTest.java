package junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import controller.TradeRankController;
import junit.framework.TestCase;
import model.Constants;
import model.SettledTrade;
import model.custom.TradeAggregrates;

public class TradeRankControllerTest extends TestCase{
TradeRankController classtoTest = new TradeRankController();
@Test
public void testSettleTrades() throws Exception {	
	applyTradeSettlement();
	List<SettledTrade> settledTrades = classtoTest.getSettledTrades();
	assertEquals(8, settledTrades.size());
	assertEquals(getDate("25/Sep/2018"), settledTrades.get(1).getSettleDate());
	assertEquals(1524.7260470632464, settledTrades.get(1).getTradeTotalValue());
}

@Test
public void testGetTradeAggregrateReport() throws Exception {
	applyTradeSettlement();
	Map<Date, TradeAggregrates> map = classtoTest.getTradeAggregrateReport();
	
	assertEquals(2, map.size());
	
	
	assertEquals(3220.5960976760834, map.get(getDate("25/SEP/2018")).getBuyGrossValue());
	assertEquals(2796.148077642545, map.get(getDate("25/SEP/2018")).getSellGrossValue());
}

@Test
public void testGetIncomingTradeRanking() throws Exception {
	applyTradeSettlement();
	Map<String, Double> map = classtoTest.getIncomingTradeRanking();
	
	assertEquals(3, map.size());
	
	
	assertEquals(1064.1120328456163, map.get("Cipla"));
	
}
@Test
public void testGetOutgoingTradeRanking() throws Exception {
	applyTradeSettlement();
	Map<String, Double> map = classtoTest.getOutgoingTradeRanking();
	
	assertEquals(3, map.size());	
	assertEquals(1524.7260470632464, map.get("Axis"));	
}


private void applyTradeSettlement() throws Exception{
	List<SettledTrade> trades = createSampleTrades();
	classtoTest.SettleTrades(trades);
}



/**
 * Method to create sample trade data.
 * @return ArrayList<SettledTrade>
 * @throws ParseException 
 */
public  ArrayList<SettledTrade> createSampleTrades() throws ParseException {
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
private Date getDate(String date) throws ParseException {		
	return format.parse(date);
}
private SimpleDateFormat format = new SimpleDateFormat(Constants.Date_Pattern);
}
