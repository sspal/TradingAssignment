package junit;

import java.util.List;

import org.junit.Test;

import controller.TradeRankController;
import junit.framework.TestCase;
import model.Constants;
import model.SettledTrade;
import model.custom.EntityDetails;
import model.custom.TradeAggregrates;
import service.TradeConsolidationServiceImpl;

public class TradeConsolidationServiceTest extends TestCase{
	TradeConsolidationServiceImpl classToTest = (TradeConsolidationServiceImpl) TradeRankController.getConsolidationService();
	@Test
	public void testNegativeGetTradeAggregate() throws Exception{
		List<SettledTrade> trades = TradeRankController.createSampleTrades();
		try {
	         classToTest.getTradeAggregate(trades);
		}catch(Exception e) {
			
		}
	}
	@Test
	public void testGetTradeAggregate() throws Exception{
		List<SettledTrade> trades = TradeRankController.createSampleTrades();
		TradeRankController.getTradeSettlementService().applySettlement(trades);		
		List<TradeAggregrates> listAggregrated  = classToTest.getTradeAggregate(trades);		
		assertEquals(2, listAggregrated.size());
		assertEquals("24/Sep/2018", listAggregrated.get(1).getSettlementDate());
		assertEquals(2023.470060724765, listAggregrated.get(1).getBuyGrossValue());
		assertEquals(2264.092061219737, listAggregrated.get(1).getSellGrossValue());
	}
	@Test
	public void testGetTradeRanking() throws Exception{
		List<SettledTrade> trades = TradeRankController.createSampleTrades();
		TradeRankController.getTradeSettlementService().applySettlement(trades);		
		List<EntityDetails> listEntity  = classToTest.getEntityRanking(trades, Constants.TRADE_BUY);		
		assertEquals(3, listEntity.size());
		assertEquals("Cipla", listEntity.get(1).getEntityName());	
		assertEquals(1463.1540451627225, listEntity.get(1).getTradeGrossValue());		             
	}
	@Test
	public void testNegativeGetTradeRanking() throws Exception{
		List<SettledTrade> trades = TradeRankController.createSampleTrades();
		try {
		 classToTest.getEntityRanking(trades, Constants.TRADE_BUY);	
		}catch(Exception e) {
			
		}             
	}
	
}
