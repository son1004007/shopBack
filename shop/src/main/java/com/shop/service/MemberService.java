package com.shop.service;

import java.util.List;

import com.shop.domain.Member;

public interface MemberService {

	public void register(Member member) throws Exception;

	public Member read(Long userNo) throws Exception;

	public void modify(Member member) throws Exception;

	public void remove(Long userNo) throws Exception;

	public List<Member> list() throws Exception;
	
	public void setupAdmin(Member member) throws Exception;
	
	public long countAll() throws Exception;
	
	public int getCoin(Long userNo) throws Exception;
}

