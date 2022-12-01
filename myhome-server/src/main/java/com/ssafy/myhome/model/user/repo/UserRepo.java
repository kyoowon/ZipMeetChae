package com.ssafy.myhome.model.user.repo;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.myhome.model.user.dto.User;

@Mapper
public interface UserRepo {
	// 로그인
	public User login(User user) throws SQLException;
	
	// id로 사용자 정보 가져오기
	public User userInfo(String userid) throws SQLException;
	
	// refreshtoken 저장
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	
	// refreshtoken 가져오기
	public Object getRefreshToken(String userid) throws SQLException;
	
	// refreshtoken 삭제하기
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
	
	// 회원등록
	public int insert(User user) throws SQLException;
	
	// 회원정보 조회
	public User selectById(String id) throws SQLException;
	
	// 회원정보 수정
	public int update(User user) throws SQLException;
	
	// 회원탈퇴
	public int deleteById(String id) throws SQLException;
	
}
