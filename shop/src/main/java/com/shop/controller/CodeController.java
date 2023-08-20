package com.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.dto.CodeLabelValue;
import com.shop.service.CodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/codes")
public class CodeController {
	
	private final CodeService codeService;
	
	// 코드 그룹 목록 조회
	@GetMapping("/codeGroup")
	public ResponseEntity<List<CodeLabelValue>> codeGroupList() throws Exception {
		log.info("codeGroupList");
		
		return new ResponseEntity<>(codeService.getCodeGroupList(), HttpStatus.OK);
	}
	
	@GetMapping("/job")
	public ResponseEntity<List<CodeLabelValue>> jobList() throws Exception {
		log.info("jobList");
		
		String classCode = "job";
		List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
		
		return new ResponseEntity<>(jobList, HttpStatus.OK);
	}
	

}
