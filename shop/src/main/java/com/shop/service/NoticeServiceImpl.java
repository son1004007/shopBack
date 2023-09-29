package com.shop.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shop.domain.Notice;
import com.shop.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository repository;
	
	@Override
	public void register(Notice notice) throws Exception {
		repository.save(notice);
	}
	
	@Override
	public List<Notice> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "noticeNo"));
	}
	
	@Override
	public Notice read(Long noticeNo) throws Exception {
		return repository.getOne(noticeNo);
	}
	
	@Override
	public void modify(Notice notice) throws Exception {
		Notice noticeEntity = repository.getOne(notice.getNoticeNo());
		
		noticeEntity.setTitle(notice.getTitle());
		noticeEntity.setContent(notice.getContent());
		
		repository.save(noticeEntity);
	}
	
	@Override
	public void remove(Long noticeNo) throws Exception {
		repository.deleteById(noticeNo);
	}
}
