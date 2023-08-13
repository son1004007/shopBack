package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.CodeGroup;

public interface CodeGroupRepository extends JpaRepository<CodeGroup, String> {
	
}
