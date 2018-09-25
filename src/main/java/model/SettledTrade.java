package model;

import java.util.Date;

/**
 * 
 * SettledTrade Class extends to add extra behaviors of actualSettlementDate and TradeTotalValue
 *
 */
public class SettledTrade extends Trade{
	private Date settleDate;
	private double tradeTotalValue;
/**
 * Constructor that sets values into super class constructor
 * @param tradeEntity
 * @param instructDate
 * @param intendedSettlementDate
 * @param currency
 * @param fxRate
 * @param units
 * @param tradeType
 * @param unitPrice
 */
	public SettledTrade(String tradeEntity, Date instructDate, Date intendedSettlementDate, String currency, float fxRate,
			long units, String tradeType, double unitPrice) {
		super(tradeEntity, instructDate, intendedSettlementDate, currency, fxRate, units, tradeType, unitPrice);
		}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public double getTradeTotalValue() {
		return tradeTotalValue;
	}

	public void setTradeTotalValue(double tradeTotalValue) {
		this.tradeTotalValue = tradeTotalValue;
	}

	
}
