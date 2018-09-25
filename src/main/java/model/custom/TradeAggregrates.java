package model.custom;

import java.util.Date;

/**
 * 
 * Class which holds trade settlement 
 * date wise buy and sell gross values
 *
 */
public class TradeAggregrates {
	private Date settlementDate;
	private double buyGrossValue;
	private double sellGrossValue;
		
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	public double getBuyGrossValue() {
		return buyGrossValue;
	}
	public void setBuyGrossValue(double buyGrossValue) {
		this.buyGrossValue = buyGrossValue;
	}
	public double getSellGrossValue() {
		return sellGrossValue;
	}
	public void setSellGrossValue(double sellGrossValue) {
		this.sellGrossValue = sellGrossValue;
	}
	

}
