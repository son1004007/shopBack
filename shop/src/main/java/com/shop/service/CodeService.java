package com.shop.service;

import java.util.List;

import com.shop.dto.CodeLabelValue;

public interface CodeService {

	// 코드 그룹 목록 조회
	public List<CodeLabelValue> getCodeGroupList() throws Exception;

	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception;
}
