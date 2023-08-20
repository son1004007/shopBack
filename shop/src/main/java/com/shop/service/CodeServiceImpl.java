package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import com.shop.repository.CodeDetailRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shop.domain.CodeDetail;
import com.shop.domain.CodeGroup;
import com.shop.dto.CodeLabelValue;
import com.shop.repository.CodeGroupRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {
	
	private final CodeGroupRepository repository;

	private final CodeDetailRepository codeDetailRepository;
	// 조회
	@Override
	public List<CodeLabelValue> getCodeGroupList() throws Exception {
		List<CodeGroup> codeGroups = repository.findAll(Sort.by(Direction.ASC, "groupCode"));
		
		List<CodeLabelValue> codeGroupList = new ArrayList<CodeLabelValue>();
		
		for(CodeGroup codeGroup : codeGroups) {
			codeGroupList.add(new CodeLabelValue(codeGroup.getGroupCode(), codeGroup.getGroupName()));
		}
		return codeGroupList;
	}
	
	@Override
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception {
		List<CodeDetail> codeDetails = codeDetailRepository.getCodeList(groupCode);
		
		List<CodeLabelValue> codeList = new ArrayList<CodeLabelValue>();
		
		for(CodeDetail codeDetail : codeDetails) {
			codeList.add(new CodeLabelValue(codeDetail.getCodeValue(), codeDetail.getCodeName()));
		}
		
		return codeList;
	}
}
