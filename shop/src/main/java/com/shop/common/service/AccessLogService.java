package com.shop.common.service;

import java.util.List;

import com.shop.domain.AccessLog;

public interface AccessLogService {

	public void register(AccessLog accessLog) throws Exception;
	
	public List<AccessLog> list() throws Exception;
}
