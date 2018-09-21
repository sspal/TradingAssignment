package model.custom;
/**
 * 
 * Class which holds trade settlement 
 * date wise buy and sell gross values
 *
 */
public class TradeAggregrates {
	private String settlementDate;
	private double buyGrossValue;
	private double sellGrossValue;
		
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
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
