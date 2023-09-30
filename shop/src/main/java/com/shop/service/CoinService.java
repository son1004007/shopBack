package com.shop.service;

import java.util.List;

import com.shop.domain.ChargeCoin;

public interface CoinService {

	public void charge(ChargeCoin chargeCoin) throws Exception;
	
	public List<ChargeCoin> list(Long userNo) throws Exception;
}
