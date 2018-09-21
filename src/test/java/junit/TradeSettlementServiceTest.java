package junit;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import controller.TradeRankController;
import junit.framework.TestCase;
import model.Constants;
import model.SettledTrade;
import service.TradeSettlementServiceImpl;

public class TradeSettlementServiceTest extends TestCase{
	TradeSettlementServiceImpl classToTest = (TradeSettlementServiceImpl) TradeRankController.getTradeSettlementService();
	@Test
	public void testApplySettlement() throws ParseException{
		ArrayList<SettledTrade> trades = TradeRankController.createSampleTrades();
		classToTest.applySettlement(trades);
		assertEquals(8, trades.size());
		assertEquals("24/Sep/2018", trades.get(1).getSettleDate());
		assertEquals(1524.7260470632464, trades.get(1).getTradeTotalValue());
	}
	@Test
	public void testNegativeApplySettlement()throws ParseException{
		ArrayList<SettledTrade> trades = new ArrayList<SettledTrade>();
		SettledTrade trade = new SettledTrade("Cipla", "21/09/2018", "21/09/2018", 
				"INR", 0.014f, 1000, Constants.TRADE_BUY, 95.01);
		trades.add(trade);
		
		try {
			classToTest.applySettlement(trades);
		}catch(ParseException ex) {
			
		}
	}
}
