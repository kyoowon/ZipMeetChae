package com.ssafy.myhome.model.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.ssafy.myhome.model.board.dto.BoardDto;
import com.ssafy.myhome.model.board.dto.BoardParameterDto;
import com.ssafy.myhome.model.board.repo.BoardRepo;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private BoardRepo brepo;

	@Override
	public boolean writeArticle(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardRepo.class).writeArticle(boardDto) == 1;
	}
	
	
	
	@Override
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(BoardRepo.class).listArticle(boardParameterDto);
	}
	
	@Override
	public Page<BoardDto> search() {
		return brepo.search();
	}


	@Override
	public BoardDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardRepo.class).getArticle(articleno);
	}
	
	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(BoardRepo.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(BoardRepo.class).modifyArticle(boardDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean modifydealState(int dealState, int articleno) throws Exception {
		// 거래상태를 반전시켜줌
		if(dealState == 1) dealState = 0;
		else dealState = 1;
		return sqlSession.getMapper(BoardRepo.class).modifydealState(dealState, articleno) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		//sqlSession.getMapper(BoardRepo.class).deleteMemo(articleno);
		return sqlSession.getMapper(BoardRepo.class).deleteArticle(articleno) == 1;
	}





}
