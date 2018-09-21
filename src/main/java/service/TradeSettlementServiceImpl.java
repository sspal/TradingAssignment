package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
	private static Calendar c = Calendar.getInstance();
	/**
	 * Simpledateformat instance
	 */
	private static SimpleDateFormat format = new SimpleDateFormat(Constants.Date_Pattern);

	private double getTotalTradeAmount(double unitPrice, long units, float fxRate) {
		return unitPrice * units * fxRate;
	}
	/**
	 * public applySettlement() method for trade settlement
	 * @param trades - List<SettledTrade>
	 * @return void
	 */
	public void applySettlement(List<SettledTrade> trades) throws ParseException {
		SettledTrade trade;
		for(int i=0; i<trades.size(); i++) {
		trade = trades.get(i);
		applyNextDay(trade);
		trade.setTradeTotalValue(getTotalTradeAmount(trade.getUnitPrice(), trade.getUnits(), trade.getFxRate()));
		}
	}
	/**
	 * private applyNextDay() method for trade settlement
	 * @param trade - SettledTrade
	 * @return void
	 * @exception ParseException
	 */
	private void applyNextDay(SettledTrade trade) throws ParseException {
		c.setTime(format.parse(trade.getIntendedSettlementDate()));
		int day = c.get(Calendar.DAY_OF_WEEK);
		if (day == 1 ){
			c.add(Calendar.DAY_OF_MONTH, 1);
			trade.setSettleDate(format.format(c.getTime()));
		}else
			if(day == 7 ){
				c.add(Calendar.DAY_OF_MONTH, 2);
				trade.setSettleDate(format.format(c.getTime()));
			}else
			if(day == 6 && (trade.getCurrency()==Constants.CURRENCY_AED || trade.getCurrency()==Constants.CURRENCY_SAR)) {
				c.add(Calendar.DAY_OF_MONTH, 3);
				trade.setSettleDate(format.format(c.getTime()));
				}
				else {
					trade.setSettleDate(trade.getIntendedSettlementDate());
				}
		
	}

}
