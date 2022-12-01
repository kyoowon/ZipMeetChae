package com.ssafy.myhome.model.board.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.ssafy.myhome.model.board.dto.BoardDto;
import com.ssafy.myhome.model.board.dto.BoardParameterDto;
public interface BoardService {
	public boolean writeArticle(BoardDto boardDto) throws Exception;
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	public Page<BoardDto> search();
	
	public BoardDto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(BoardDto boardDto) throws Exception;
	public boolean modifydealState(int dealState, int articleno) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
}
