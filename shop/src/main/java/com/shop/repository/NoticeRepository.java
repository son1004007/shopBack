package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
