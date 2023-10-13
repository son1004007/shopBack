package com.shop.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.common.domain.PerformanceLog;

public interface PerformanceLogRepository extends JpaRepository<PerformanceLog, Long>{

}
