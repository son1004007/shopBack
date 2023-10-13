package com.shop.common.service;

import java.util.List;

import com.shop.common.domain.PerformanceLog;

public interface PerformanceLogService {

	public void register(PerformanceLog performanceLog) throws Exception;

	public List<PerformanceLog> list() throws Exception;
}
