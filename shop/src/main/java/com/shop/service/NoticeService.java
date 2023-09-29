package com.shop.service;

import java.util.List;

import com.shop.domain.Notice;

public interface NoticeService {

	public void register(Notice notice) throws Exception;
	
	public List<Notice> list() throws Exception;
	
	public Notice read(Long noticeNo) throws Exception;

	public void modify(Notice notice) throws Exception;
	
	public void remove(Long noticeNo) throws Exception;
}
