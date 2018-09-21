package service;

import java.util.Comparator;

import model.custom.EntityDetails;
/**
 * 
 * class that implments comparator to sort EntityDetails
 * sorting by TradeGrossValue
 *
 */
public class RatingCompare implements Comparator<EntityDetails> 
{ 
	/**
	 * 
	 * overriden compare method that to sort EntityDetails
	 * sorting by TradeGrossValue
	 *
	 */
 	public int compare(EntityDetails ent1, EntityDetails ent2) {
		 if (ent1.getTradeGrossValue() < ent2.getTradeGrossValue()) return -1; 
	        if (ent1.getTradeGrossValue() > ent2.getTradeGrossValue()) return 1; 
	        else return 0; 
	} 
}
