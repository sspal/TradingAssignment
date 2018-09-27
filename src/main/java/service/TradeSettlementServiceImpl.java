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
	private void applyNextDay(SettledTrade trade) {
		c.setTime(trade.getIntendedSettlementDate());
		
		
		boolean nonGulfCurrency = checkNonGulfCurrency(trade.getCurrency());
		
		int dayInput = c.get(Calendar.DAY_OF_WEEK);
		int dayDjustment = 0;
		
		switch(dayInput) {
		case 1 : {
			dayDjustment = (nonGulfCurrency) ? 3 : 2;			
			break;
		}
		case 2 : {
			dayDjustment = 2;			
			break;
		}
		case 3 : {
			dayDjustment = 2;			
			break;
		}
		case 4 : {
			dayDjustment = (nonGulfCurrency) ? 2 : 4;	
			break;
		}
		case 5 : {
			dayDjustment =  4;	
			break;
		}
		case 6 :  {
			dayDjustment =  4;	
			break;
		}
		case 7 :  {
			dayDjustment =  (nonGulfCurrency) ? 4 : 3;
			break;
		}		
	}
		c.add(Calendar.DAY_OF_MONTH, dayDjustment);
		trade.setSettleDate(c.getTime());		
	}
	
	private boolean checkNonGulfCurrency(String currency) {
		return (currency != Constants.CURRENCY_AED && currency != Constants.CURRENCY_SAR);
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
