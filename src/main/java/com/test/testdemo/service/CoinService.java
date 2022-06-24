package com.test.testdemo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testdemo.model.Coin;
import com.test.testdemo.model.CoinRepository;

//使用GitHub好心人提供的API，不會有每日上限的問題
@Service
@Transactional
public class CoinService {
	
	@Autowired
	private CoinRepository dao;
	
	//資料來源API
	private String coinURL = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=10&sortBy=market_cap&sortType=desc&convert=USD&cryptoType=all&tagType=all&audited=false";
	
	//把傳入的URL轉成JSONArray
	public String getCoinInfo() {
		try {
			URL url = new URL(coinURL);
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while((s=bf.readLine()) != null) {
				sb.append(s+"/r/n");
			}
			bf.close();
			String sbString = sb.toString();
			sbString = sbString.substring(sbString.indexOf("["), sbString.lastIndexOf("]")+1);
			return sbString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//抓API資料並更新SQL
	public void updateCoin(String sbString) {
		JSONArray jsonArray = new JSONArray(sbString);
		int i = 0;
		for(Object obj : jsonArray) {
			i++;
			JSONObject jsonObject = (JSONObject)obj;
			System.out.println(jsonObject);
			String name = jsonObject.getString("symbol");
			BigDecimal price = jsonObject.getJSONArray("quotes").getJSONObject(0).getBigDecimal("price");
			String last_updated = jsonObject.getString("lastUpdated");
			Coin coin = new Coin();
			coin.setCoinId(i);
			coin.setCoinName(name);
			coin.setUSD(price);
			coin.setLastUpdated(last_updated);
			saveCoin(coin);
		}
	}
	
	//儲存
	public void saveCoin(Coin coin) {
		dao.save(coin);
	}
	
	public List<Coin> getAllCoin(){
		List<Coin> findAll = dao.findAll();
		return findAll;
	}
}
