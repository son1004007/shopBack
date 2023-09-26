package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	
}
