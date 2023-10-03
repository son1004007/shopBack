package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.PdsFile;

public interface PdsFileRepository  extends JpaRepository<PdsFile, Long> {

	public List<PdsFile> findByFullName(String fullName);
	
}
