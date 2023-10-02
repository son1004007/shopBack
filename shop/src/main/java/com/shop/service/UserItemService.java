package com.shop.service;

import java.util.List;

import com.shop.domain.Item;
import com.shop.domain.Member;
import com.shop.domain.UserItem;

public interface UserItemService {
	
	public void register(Member memger, Item item) throws Exception;

	public UserItem read(Long userItemNo) throws Exception;

	public List<UserItem> list(Long userNo) throws Exception;

}
