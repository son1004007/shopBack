package com.shop.service;

import java.util.List;

import com.shop.domain.Board;

public interface BoardService {
	
	public void register(Board board) throws Exception;

	public Board read(Long boardNo) throws Exception;

	public void modify(Board board) throws Exception;

	public void remove(Long boardNo) throws Exception;

	public List<Board> list() throws Exception;
	
	
}
