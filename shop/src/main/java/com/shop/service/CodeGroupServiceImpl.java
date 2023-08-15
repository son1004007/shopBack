package com.shop.service;

import java.util.List;

import com.shop.domain.CodeGroup;
import com.shop.repository.CodeGroupRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CodeGroupServiceImpl implements CodeGroupService {

	private final CodeGroupRepository repository;
	
	// 목록
	@Override
	public List<CodeGroup> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "groupCode"));
	}
	
	// 등록
	@Override
	public void register(CodeGroup codeGroup) throws Exception {
		repository.save(codeGroup);
	}

	// 수정
	@Override
	public void modify(CodeGroup codeGroup) throws Exception {
		CodeGroup codeGroupEntity = repository.getOne(codeGroup.getGroupCode());

		codeGroupEntity.setGroupName(codeGroup.getGroupName());
		
		repository.save(codeGroupEntity);
	}

	// 삭제
	@Override
	public void remove(String groupCode) throws Exception {
		repository.deleteById(groupCode);
	}
	
	// 상세
	@Override
	public CodeGroup read(String groupCode) throws Exception {
		return repository.getOne(groupCode);
	}

}
