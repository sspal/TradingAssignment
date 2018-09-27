package service;

import java.util.Comparator;
import java.util.HashMap;
/**
 * 
 * class that implements comparator to sort EntityDetails
 * sorting by TradeGrossValue
 *
 */
public class RatingCompare implements  Comparator<Object> 
{ 
	
	 
		HashMap<String, Double> map;
	    public RatingCompare(HashMap<String, Double> map) {
	        this.map = map;
	    }
	/**
	 * 
	 * overriden compare method that to sort EntityDetails
	 * sorting by TradeGrossValue
	 */
	
    public int compare(Object o1, Object o2) {
        return ((Double) map.get(o2)).compareTo((Double) map.get(o1));
    }
}
