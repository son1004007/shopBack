package com.shop.service;

import java.util.List;

import com.shop.domain.CodeDetail;

public interface CodeDetailService {
	
	// 조회
	public List<CodeDetail> list() throws Exception;
	
	// 등록
	public void register(CodeDetail codeDetail) throws Exception;
	
	// 수정
	public void modify(CodeDetail codeDetail) throws Exception;
	
	// 삭제
	public void remove(CodeDetail codeDetail) throws Exception;

	// 상세 조회
	public CodeDetail read(CodeDetail codeDetail) throws Exception;
}
