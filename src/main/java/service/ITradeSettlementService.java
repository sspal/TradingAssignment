package service;

import java.util.List;

import model.SettledTrade;
/**
 * 
 * Trade Settlement Service Interface
 *
 */
public interface ITradeSettlementService {
	/**
	 * public applySettlement() method for trade settlement
	 * @param trades - List<SettledTrade>
	 * @return void
	 */
	void applySettlement(List<SettledTrade> trades) throws Exception;
}
