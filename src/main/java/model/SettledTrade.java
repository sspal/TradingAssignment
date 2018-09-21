package model;
/**
 * 
 * SettledTrade Class extends to add extra behaviours of actualSettlementDate and TradeTotalValue
 *
 */
public class SettledTrade extends Trade{
	private String settleDate;
	private double tradeTotalValue;
/**
 * Contructor that sets values into super class constructor
 * @param tradeEntity
 * @param instructDate
 * @param intendedSettlementDate
 * @param currency
 * @param fxRate
 * @param units
 * @param tradeType
 * @param unitPrice
 */
	public SettledTrade(String tradeEntity, String instructDate, String intendedSettlementDate, String currency, float fxRate,
			long units, String tradeType, double unitPrice) {
		super(tradeEntity, instructDate, intendedSettlementDate, currency, fxRate, units, tradeType, unitPrice);
		}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public double getTradeTotalValue() {
		return tradeTotalValue;
	}

	public void setTradeTotalValue(double tradeTotalValue) {
		this.tradeTotalValue = tradeTotalValue;
	}

	
}
