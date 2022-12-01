package com.ssafy.myhome.model.board.repo;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.ssafy.myhome.model.board.dto.BoardDto;
import com.ssafy.myhome.model.board.dto.BoardParameterDto;

@Mapper
public interface BoardRepo {
	// 글작성
	public int writeArticle(BoardDto boardDto) throws SQLException;

	// 글전체 조회 + 검색 기능
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws SQLException;

	public Page<BoardDto> search();

	// 글 1개 조회
	public BoardDto getArticle(int articleno) throws SQLException;

	// 조회수 증가
	public void updateHit(int articleno) throws SQLException;

	// 글 수정
	public int modifyArticle(BoardDto boardDto) throws SQLException;

	// public void deleteMemo(int articleno) throws SQLException;

	// 글 삭제
	public int deleteArticle(int articleno) throws SQLException;

	// 거래상태 업데이트
	public int modifydealState(int dealState, int articleno) throws SQLException;
}
