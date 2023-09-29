package com.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.Notice;
import com.shop.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notices")
public class NoticeController {

	private final NoticeService service;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Notice> register(@Validated @RequestBody Notice notice) throws Exception {
		log.info("register");
		
		service.register(notice);
		
		log.info("register notice.getNoticeNo() = " + notice.getNoticeNo());
		
		return new ResponseEntity<>(notice, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Notice>> list() throws Exception {
		log.info("list");
		
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{noticeNo}")
	public ResponseEntity<Notice> read(@PathVariable("noticeNo") Long noticeNo) throws Exception {
		Notice notice = service.read(noticeNo);
		
		return new ResponseEntity<>(notice, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{noticeNo}")
	public ResponseEntity<Notice> modify(@PathVariable("noticeNo") Long noticeNo, @Validated @RequestBody Notice notice) throws Exception {
		log.info("noticeNo", noticeNo);
		notice.setNoticeNo(noticeNo);
		service.modify(notice);
		
		return new ResponseEntity<>(notice, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{noticeNo}")
	public ResponseEntity<Void> remove(@PathVariable("noticeNo") Long noticeNo ) throws Exception {
		service.remove(noticeNo);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
