package service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import dao.ISettledTradesDao;
import dao.SettledTradesDaoImpl;
import model.Constants;
import model.SettledTrade;
/**
 * 
 * Trade Settlement Service Class
 *
 */
public class TradeSettlementServiceImpl implements ITradeSettlementService{
	/**
	 * Calendar instance
	 */
	private Calendar c = Calendar.getInstance();
	

	private double getTotalTradeAmount(double unitPrice, long units, float fxRate) {
		return unitPrice * units * fxRate;
	}
	/**
	 * public applySettlement() method for trade settlement
	 * @param trades - List<SettledTrade>
	 * @return void
	 * @throws Exception 
	 */
	public void applySettlement(List<SettledTrade> trades) throws Exception {		
		dao = getSettledTradesDao();
		for(SettledTrade trade : trades) {
		applyNextDay(trade);
		trade.setTradeTotalValue(getTotalTradeAmount(trade.getUnitPrice(), trade.getUnits(), trade.getFxRate()));
		dao.saveSettledTrades(trades);
		}
	}
	/**
	 * private applyNextDay() method for trade settlement
	 * @param trade - SettledTrade
	 * @return void
	 * @exception ParseException
	 */
	private void applyNextDay(SettledTrade trade) throws ParseException {
		c.setTime(trade.getIntendedSettlementDate());
		int day = c.get(Calendar.DAY_OF_WEEK);
		if (day == 1 ){
			c.add(Calendar.DAY_OF_MONTH, 1);
			trade.setSettleDate(c.getTime());
		}else
			if(day == 7 ){
				c.add(Calendar.DAY_OF_MONTH, 2);
				trade.setSettleDate(c.getTime());
			}else
			if(day == 6 && (trade.getCurrency()==Constants.CURRENCY_AED || trade.getCurrency()==Constants.CURRENCY_SAR)) {
				c.add(Calendar.DAY_OF_MONTH, 3);
				trade.setSettleDate(c.getTime());
				}
				else {
					trade.setSettleDate(trade.getIntendedSettlementDate());
				}
		
	}
	
	/**
	 * ISettledTradesDao reference.
	 */
	private ISettledTradesDao dao;
	/**
	 * ISettledTradesDao instance generation if its null else returns existing instance.
	 */
	public ISettledTradesDao getSettledTradesDao() {
	if(dao == null) {
		dao = new SettledTradesDaoImpl(); 
		return dao;
	}
	else {
		return dao;
	}
}
	public List<SettledTrade> getSettledTrades() throws Exception {
		dao = getSettledTradesDao();
		return dao.getSettledTrades();
	}

}
