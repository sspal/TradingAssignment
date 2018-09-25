package dao;

import java.util.List;

import model.SettledTrade;
/**
 * class for Storing and retrieving trades in memory
 */
public interface ISettledTradesDao {
	/**
	 * public saveSettledTrades() method for storing settled trades in Memory(DB)
	 * @param trades - List<SettledTrade>
	 * @return void
	 */
	void saveSettledTrades(List<SettledTrade> trades) throws Exception;
	/**
	 * public getSettledTrades() method for retrieving settled trades from Memory(DB)
	 
	 * @return List<SettledTrade>
	 */
	List<SettledTrade> getSettledTrades() throws Exception;
}
