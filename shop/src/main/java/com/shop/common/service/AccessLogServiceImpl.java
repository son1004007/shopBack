package com.shop.common.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shop.common.repository.AccessLogRepository;
import com.shop.domain.AccessLog;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccessLogServiceImpl implements AccessLogService {

	private final AccessLogRepository repository;
	
	@Override
	public void register(AccessLog accessLog) throws Exception {
		repository.save(accessLog);
	}
	
	@Override
	public List<AccessLog> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "logNo"));
	}
}
