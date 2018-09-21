package model.custom;
/**
 * 
 * Custom object for consolidation of entityname wise grossvalues
 *
 */
public class EntityDetails{
	private String entityName;
	private double tradeGrossValue;

	public double getTradeGrossValue() {
		return tradeGrossValue;
	}
	public void setTradeGrossValue(double tradeGrossValue) {
		this.tradeGrossValue = tradeGrossValue;
	}
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
		
}
