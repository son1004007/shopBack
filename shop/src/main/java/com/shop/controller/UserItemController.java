package com.shop.controller;

import java.io.File;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.common.exception.NotMyItemException;
import com.shop.common.security.domain.CustomUser;
import com.shop.domain.UserItem;
import com.shop.prop.ShopProperties;
import com.shop.service.UserItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/useritems")
public class UserItemController {
	
	private final UserItemService service;
	
	private final ShopProperties shopProperties;
	
	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
	@GetMapping
	public ResponseEntity<List<UserItem>> list (
			@AuthenticationPrincipal 
			CustomUser customUser
			) throws Exception {
		
		Long userNo = customUser.getUserNo();
		
		log.info("read: userNo" + userNo);
		
		return new ResponseEntity<>(service.list(userNo), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
	@GetMapping("/{userItemNo}")
	public ResponseEntity<UserItem> read (
			
			@PathVariable("userItemNo") 
			Long userItemNo
			
			) throws Exception {
		UserItem userItem = service.read(userItemNo);
		
		return new ResponseEntity<>(userItem, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MEMBER')")
	@GetMapping("/download/{userItemNo}")
	public ResponseEntity<byte[]> download (
			
			@PathVariable("userItemNo") 
			Long userItemNo,
			
			@AuthenticationPrincipal
			CustomUser customUser
			
			) throws Exception {
		
		log.info("download userItemNo = " + userItemNo);
		
		UserItem userItem = service.read(userItemNo);
		
		// 구매 상품이 사용자 것인지 체크
		Long userNo = customUser.getUserNo();
		log.info("download userNo = " + userNo);
		
		if (userItem.getUserNo() != userNo) {
			throw new NotMyItemException("It is Not My Item");
		}
		
		String fullName = userItem.getPictureUrl();
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			
			File file = new File(shopProperties.getUploadPath() + File.separator + fullName);
			
			String fileName = fullName.substring(fullName.indexOf("_") + 1);
			
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") +"\"");
			
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

}
