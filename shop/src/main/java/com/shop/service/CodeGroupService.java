package com.shop.service;

import java.util.List;

import com.shop.domain.CodeGroup;

public interface CodeGroupService {
	
	// 조회
	public List<CodeGroup> list() throws Exception;
	
	// 등록
	public void register(CodeGroup codeClass) throws Exception;

	// 수정
	public void modify(CodeGroup codeClass) throws Exception;
	
	// 삭제
	public void remove(String codeClass) throws Exception;
	
	// 상세
	public CodeGroup read(String codeClass) throws Exception;
}
