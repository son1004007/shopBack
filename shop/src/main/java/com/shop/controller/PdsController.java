package com.shop.controller;

import java.io.File;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.common.util.UploadFileUtils;
import com.shop.domain.Pds;
import com.shop.prop.ShopProperties;
import com.shop.service.PdsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pds")
public class PdsController {
	
	private final PdsService pdsService;
	
	private final ShopProperties shopProperties;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Pds> register(
			@Validated
			@RequestBody
			Pds pds
			) throws Exception {
		this.pdsService.register(pds);
		
		log.info("register pds.getItemId() = " + pds.getItemId());
		
		return new ResponseEntity<>(pds, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Pds>> list() throws Exception {
		return new ResponseEntity<>(this.pdsService.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{itemId}")
	public ResponseEntity<Pds> read(
			@PathVariable("itemId")
			Long itemId
			) throws Exception {
		Pds pds = this.pdsService.read(itemId);
		
		return new ResponseEntity<>(pds, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{itemId}")
	public ResponseEntity<Pds> modify(
			@PathVariable("itemId")
			Long itemId,
			@Validated
			@RequestBody 
			Pds pds
			) throws Exception {
		pds.setItemId(itemId);
		this.pdsService.modify(pds);
		
		return new ResponseEntity<>(pds, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{itemId}")
	public ResponseEntity<Void> remove(
			@PathVariable("itemId")
			Long itemId
			) throws Exception {
		this.pdsService.remove(itemId);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/upload", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> upload(MultipartFile file) throws Exception {
		String savedName = UploadFileUtils.uploadFile(shopProperties.getUploadPath(), file.getOriginalFilename(), file.getBytes());
		
		return new ResponseEntity<String>(savedName, HttpStatus.CREATED);
	}
	
	@GetMapping("/attach/{itemId}")
	public List<String> attach(@PathVariable("itemId") Long itemId) throws Exception {
		return pdsService.getAttach(itemId);
	}
	
	@GetMapping("/download")
	public ResponseEntity<byte[]> download(String fullName) throws Exception {
		ResponseEntity<byte[]> entity = null;
		
		pdsService.updateAttachDownCnt(fullName);
		
		try {
			HttpHeaders headers = new HttpHeaders();
			
			File file = new File(shopProperties.getUploadPath() + File.separator + fullName);
			
			String fileName = fullName.substring(fullName.indexOf("_") + 1);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
