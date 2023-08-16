package com.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.CodeDetail;
import com.shop.service.CodeDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/codedetails")
public class CodeDetailController {
	
	private final CodeDetailService codeDetailService;
	
	// 상세조회
	@GetMapping("/{groupCode}/{codeValue}")
	public ResponseEntity<CodeDetail> read(@PathVariable("groupCode") String groupCode, @PathVariable("codeValue") String codeValue) throws Exception {
		CodeDetail codeDetail = new CodeDetail();
		codeDetail.setGroupCode(groupCode);
		codeDetail.setCodeValue(codeValue);
		
		return new ResponseEntity<>(codeDetailService.read(codeDetail), HttpStatus.OK);
	}
	
	// 목록조회
	@GetMapping
	public ResponseEntity<List<CodeDetail>> list() throws Exception {
		log.info("list");
		
		return new ResponseEntity<>(codeDetailService.list(), HttpStatus.OK);
	}
	
	// 등록
	@PostMapping
	public ResponseEntity<CodeDetail> register(@Validated @RequestBody CodeDetail codeDetail) throws Exception {
		log.info("register");
		
		codeDetailService.register(codeDetail);
		
		log.info("register codeDetail.getCodeClassNo()=" + codeDetail.getGroupCode());
		log.info("register codeDetail.getCodeValue()=" + codeDetail.getCodeValue());
		
		return new ResponseEntity<>(codeDetail, HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/{groupCode}/{codeValue}")
	public ResponseEntity<Void> remove(@PathVariable("groupCode") String groupCode, @PathVariable("codeValue") String codeValue) throws Exception {
		CodeDetail codeDetail = new CodeDetail();
		codeDetail.setGroupCode(groupCode);
		codeDetail.setCodeValue(codeValue);
		
		codeDetailService.remove(codeDetail);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// 수정
	@PutMapping("/{groupCode}/{codeValue}")
	public ResponseEntity<CodeDetail> modify(@PathVariable("groupCode") String groupCode, @PathVariable("codeValue") String codeValue, @Validated @RequestBody CodeDetail codeDetail) throws Exception {
		codeDetail.setGroupCode(groupCode);
		codeDetail.setCodeValue(codeValue);
		
		codeDetailService.modify(codeDetail);
		
		return new ResponseEntity<>(codeDetail, HttpStatus.OK);
	}

}
