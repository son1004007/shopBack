package com.shop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shop.domain.ChargeCoin;
import com.shop.domain.Member;
import com.shop.repository.ChargeCoinRepository;
import com.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CoinServiceImpl implements CoinService {
	
	private final ChargeCoinRepository chargeCoinRepository;
	
	private final MemberRepository memberRepository;

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
}
