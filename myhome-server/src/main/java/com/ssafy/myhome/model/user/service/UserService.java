package com.ssafy.myhome.model.user.service;

import java.sql.SQLException;

import com.ssafy.myhome.model.user.dto.User;

public interface UserService {
	public User login(User user) throws Exception;

	public User userInfo(String userid) throws Exception;

	public void saveRefreshToken(String userid, String refreshToken) throws Exception;

	public Object getRefreshToken(String userid) throws Exception;

	public void deleRefreshToken(String userid) throws Exception;

	// 회원등록
	public int insert(User user) throws SQLException;;

	// 회원정보 조회
	public User selectById(String id) throws SQLException;;

	// 회원정보 수정
	public int update(User user) throws SQLException;;

	// 회원탈퇴
	public int deleteById(String id) throws SQLException;;
}
