package com.shop.common.security;

import com.shop.common.security.domain.CustomUser;
import com.shop.domain.Member;
import com.shop.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		log.info("userName : " + userName);
		
		Member member = repository.findByUserId(userName).get(0);
		
		log.info("member : " + member);
		
		return member == null ? null : new CustomUser(member);
	}
}
