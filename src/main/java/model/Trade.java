package model;

import java.util.Random;

public class Trade {
	/**
	 * random generated tradeid
	 */
	private long tradeId;
	/**
	 * tradeEntity
	 */
	private String tradeEntity;
	/**
	 * instructDate
	 */
	private String instructDate;
	/**
	 * intendedSettlementDate
	 */
	private String intendedSettlementDate;
	/**
	 * currency
	 */
	private String currency;
	/**
	 * fxRate
	 */
	private float fxRate;
	/**
	 * units
	 */
	private long units;
	/**
	 * tradeType
	 */
	private String tradeType;
	/**
	 * unitPrice
	 */
	private double unitPrice;
	
	Random rand = new Random(); 
	/**
	 * Constructor to set object
	 * @param tradeEntity
	 * @param instructDate
	 * @param intendedSettlementDate
	 * @param currency
	 * @param fxRate
	 * @param units
	 * @param tradeType
	 * @param unitPrice
	 */
	public Trade(String tradeEntity, String instructDate, 
			String intendedSettlementDate, String currency, float fxRate,
			long units, String tradeType, double unitPrice) {
		long rand_long = rand.nextLong(); 
		this.tradeId = rand_long;
		this.tradeEntity = tradeEntity;
		this.instructDate = instructDate;
		this.intendedSettlementDate = intendedSettlementDate;
		this.currency = currency;
		this.fxRate = fxRate;
		this.units = units;
		this.tradeType = tradeType;
		this.unitPrice = unitPrice;
	}


	public long getTradeId() {
		return tradeId;
	}


	public String getTradeEntity() {
		return tradeEntity;
	}


	public String getInstructDate() {
		return instructDate;
	}


	public String getIntendedSettlementDate() {
		return intendedSettlementDate;
	}


	public String getCurrency() {
		return currency;
	}


	public float getFxRate() {
		return fxRate;
	}


	public long getUnits() {
		return units;
	}


	public String getTradeType() {
		return tradeType;
	}


	public double getUnitPrice() {
		return unitPrice;
	}

	
}
