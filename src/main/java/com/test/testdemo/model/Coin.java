package com.test.testdemo.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coin")
public class Coin {
	@Id
	@Column(name="coinId")
	private int coinId;
	
	@Column(name="coinName")
	private String coinName;
	
	@Column(name="USD")
	private BigDecimal USD;
	
	@Column(name="last_updated")
	private String lastUpdated;

	public int getCoinId() {
		return coinId;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setCoinId(int coinId) {
		this.coinId = coinId;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public BigDecimal getUSD() {
		return USD;
	}

	public void setUSD(BigDecimal uSD) {
		USD = uSD;
	}

}
