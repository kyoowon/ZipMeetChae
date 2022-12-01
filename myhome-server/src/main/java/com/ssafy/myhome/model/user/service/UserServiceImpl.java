package com.ssafy.myhome.model.user.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myhome.model.user.dto.User;
import com.ssafy.myhome.model.user.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo uRepo;

	@Autowired
	private SqlSession sqlsession;

	@Override
	public User login(User user) throws Exception {
		if (user.getId() == null || user.getPassword() == null)
			return null;
		return sqlsession.getMapper(UserRepo.class).login(user);
	}

	@Override
	public User userInfo(String userid) throws Exception {
		return sqlsession.getMapper(UserRepo.class).userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		sqlsession.getMapper(UserRepo.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlsession.getMapper(UserRepo.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", null);
		sqlsession.getMapper(UserRepo.class).deleteRefreshToken(map);
	}

	@Override
	public User selectById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	};

	// 회원등록
	public int insert(User user) throws SQLException {
		return uRepo.insert(user);
	};

	// 회원정보 조회
	public User selectById(String id, String password) throws SQLException {
		User user = uRepo.selectById(id);
		if (user == null) {
			return null;
		} else if (password.equals(user.getPassword())) {
			User loginUser = new User(user.getId(), user.getPassword(), user.getName(), user.getAddress(),
					user.getPhone(), user.getToken(), user.getJoinDate());
			return loginUser;
		} else {
			return null;
		}
	};

	// 회원정보 수정
	public int update(User user) throws SQLException {
		return uRepo.update(user);
	};

	// 회원탈퇴
	public int deleteById(String id) throws SQLException {
		return uRepo.deleteById(id);
	}

}
