package com.shop.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.AccessLog;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long>{

}
