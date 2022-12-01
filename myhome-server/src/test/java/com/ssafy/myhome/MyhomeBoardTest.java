package com.ssafy.myhome;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.ssafy.myhome.board.util.PageNavigationForPageHelper;
import com.ssafy.myhome.model.board.dto.BoardDto;
import com.ssafy.myhome.model.board.dto.BoardParameterDto;
import com.ssafy.myhome.model.board.repo.BoardRepo;
import com.ssafy.myhome.model.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MyhomeBoardTest {
	@Autowired
	BoardService service;
	
	@Value("${paging.perpage}")
	int per;
	
	@Autowired
	BoardRepo brepo;
	
//	@Test
//	public void pagingTest() {
//		PageHelper.startPage(1, per);
//		Page<BoardDto> info = service.search();
//		PageNavigationForPageHelper helper = new PageNavigationForPageHelper(info, "/test");
//		String navigationText = helper.getNavigation();
//		List<BoardDto> list = (List<BoardDto>) helper.getPage().getResult();
//		for(BoardDto board : list) {
//			log.debug("board: {}", board);
//		}
//	}
	
    // paging 테스트
    @Test
    public void search() {
    	// given
    	int pageNo = 1;
    	int per = 5;
    	PageHelper.startPage(pageNo, per);
    	//when
    	Page<BoardDto> page = brepo.search();
    	//then
    	long total = page.getTotal();
    	int per2 = page.getPageSize();
    	int current = page.getPageNum();
//    	assertEquals(total, 4);
    	log.debug("total: {}", total);
    	log.debug("per2: {}", per2);
    	log.debug("current: {}", current);
    }
    
    // 글작성 테스트
    @Test
    public void inserSuccessTest() {
    	// given
        BoardDto board = BoardDto.builder().userid("ssafy3").subject("제목").content("내용").build();
        // when
        int result;
		try {
			result = brepo.writeArticle(board);
			// then
			log.debug("insert result: {}", result);
	        //assertEquals(result, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // 글수정 테스트
    @Test
    public void updateTest() {
    	BoardDto board = BoardDto.builder().subject("제목수정").content("내용수정").articleno(6).build();
        int result;
		try {
			result = brepo.modifyArticle(board);
			log.debug("update result: {}", result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    // 글 삭제 테스트
    @Test
    public void deleteTest() {
        int articleno = 6;
        int result;
		try {
			result = brepo.deleteArticle(articleno);
			log.debug("delete result: {}", result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    // 글 1개 조회 테스트
    @Test
    public void getArticleTest() {
        int articleno = 1;
        BoardDto result;
		try {
			result = brepo.getArticle(articleno);
			log.debug("getArticle result: {}", result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //assertEquals(result.getAuthor(), "ssafy");
    }
    
    // 조회수 증가 테스트
    @Test
    public void updateHitTest() {
        int articleno = 1;
        BoardDto result;
		try {
			brepo.updateHit(articleno);
			result = brepo.getArticle(articleno);
			log.debug("getArticle result: {}", result.getHit());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //assertEquals(result.getAuthor(), "ssafy");
    }
    
    // 검색 조회 테스트
    @Test
    public void listArticleTest() {
    	BoardParameterDto param = BoardParameterDto.builder().key("subject").word("Vue").build();
        try {
			List<BoardDto> result = brepo.listArticle(param);
			log.debug("searchArticle result: {}", result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
