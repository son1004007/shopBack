package com.shop.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shop.domain.Item;
import com.shop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

	private final ItemRepository repository;
	
	@Override
	public void register(Item item) throws Exception {
		repository.save(item);
	}
	
	@Override
	public List<Item> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "itemId"));
	}
	
	@Override
	public Item read(Long itemId) throws Exception {
		return repository.getOne(itemId);
	}
	
	@Override
	public void modify(Item item) throws Exception {
		Item itemEntity = repository.getOne(item.getItemId());
		
		itemEntity.setItemName(item.getItemName());
		itemEntity.setPrice(item.getPrice());
		itemEntity.setDescription(item.getDescription());
		itemEntity.setPictureUrl(item.getPictureUrl());
		itemEntity.setPreviewUrl(item.getPreviewUrl());
		
		repository.save(itemEntity);
	}
	
	@Override
	public void remove(Long itemId) throws Exception {
		repository.deleteById(itemId);
	}
	
	@Override
	public String getPicture(Long itemId) throws Exception {
		Item item = repository.getOne(itemId);
		return item.getPictureUrl();
	}
	
	@Override
	public String getPreview(Long itemId) throws Exception {
		Item item = repository.getOne(itemId);
		return item.getPreviewUrl();
	}
}
