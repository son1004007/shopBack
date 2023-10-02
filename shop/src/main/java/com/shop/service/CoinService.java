package com.shop.service;

import java.util.List;

import com.shop.domain.ChargeCoin;
import com.shop.domain.PayCoin;

public interface CoinService {

	public void charge(ChargeCoin chargeCoin) throws Exception;
	
	public List<ChargeCoin> list(Long userNo) throws Exception;

	public List<PayCoin> listPayHistory(Long userNo) throws Exception;

}
