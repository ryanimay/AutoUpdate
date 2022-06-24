package com.test.testdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.testdemo.model.Coin;
import com.test.testdemo.service.CoinService;

@Component //搭配springBootApplication的@EnableScheduling
@Controller
public class CoinController {
	
	@Autowired
	private CoinService service;
	
	//啟動後延遲2秒，之後每30秒執行一次
	@Scheduled(initialDelay = 2000, fixedRate = 30000)
	@GetMapping("updateCoin")
	@ResponseBody
	public void updateCoin(){
		service.updateCoin(service.getCoinInfo());
	}
	
	@GetMapping("getCoin")
	@ResponseBody
	public List<Coin> showCoin(){
		return service.getAllCoin();
	}
}
