package com.shop.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shop.domain.ChargeCoin;
import com.shop.domain.Member;
import com.shop.domain.PayCoin;
import com.shop.repository.ChargeCoinRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.PayCoinRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CoinServiceImpl implements CoinService {
	
	private final ChargeCoinRepository chargeCoinRepository;
	
	private final MemberRepository memberRepository;
	
	private final PayCoinRepository payCoinRepository;

	@Transactional
	@Override
	public void charge(ChargeCoin chargeCoin) throws Exception {
		Member memberEntity = memberRepository.getOne(chargeCoin.getUserNo());
		
		int coin = memberEntity.getCoin();
		int amount = chargeCoin.getAmount();
		
		memberEntity.setCoin(coin + amount);
		
		memberRepository.save(memberEntity);
		
		chargeCoinRepository.save(chargeCoin);
	}
	
	@Override
	public List<ChargeCoin> list(Long userNo) throws Exception {
		return chargeCoinRepository.findAll(Sort.by(Direction.DESC, "historyNo"));
	}

	@Override
	public List<PayCoin> listPayHistory(Long userNo) throws Exception {
		List<Object[]> valueArrays = payCoinRepository.listPayHistory(userNo);
		
		List<PayCoin> payCoinList = new ArrayList<PayCoin>();
		for (Object[] valueArray : valueArrays) {
			PayCoin payCoin = new PayCoin();
			
			payCoin.setHistoryNo((Long)valueArray[0]);
			payCoin.setUserNo((Long)valueArray[1]);
			payCoin.setItemId((Long)valueArray[2]);
			payCoin.setItemName((String)valueArray[3]);
			payCoin.setAmount((int)valueArray[4]);
			payCoin.setRegDate((LocalDateTime)valueArray[5]);
			
			payCoinList.add(payCoin);
		}
		return payCoinList;
	}
	
	
}
