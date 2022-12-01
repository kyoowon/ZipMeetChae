package com.ssafy.myhome;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.myhome.model.user.dto.User;
import com.ssafy.myhome.model.user.repo.UserRepo;
import com.ssafy.myhome.model.user.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class MyhomeUserTest {

	@Autowired
	UserRepo uRepo;
	
	@Test
	public void insertTest() {
		try {
			User user = User.builder().id("ssafy4").password("1233").name("ssafy").address("test").phone("1234").build();
			int result;
			result = uRepo.insert(user);
			log.info("user {}", user);
			log.info("result {}", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// assertEquals(1, result);
	}
	@Test
	public void updateTest() {
		try {
			User user = User.builder().id("ssafy4").password("1233").name("ssafy").address("test").phone("1234").build();
			user = User.builder().id("ssafy4").password("1234").name("ssafy").address("test").phone("1234").build();
			int result = uRepo.update(user);
			result = uRepo.insert(user);
			log.info("result {}", result);
			log.info("user {}", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// assertEquals(1, result);
	}
	@Test
	public void deleteTest() {
		try {
			User user = User.builder().id("ssafy4").password("1233").name("ssafy").address("test").phone("1234").build();
			uRepo.insert(user);
			int result = uRepo.deleteById("ssafy4");
			log.info("result {}", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// assertEquals(result, 1);
	}
	@Test
	public void selectTest() {
		try {
			User user = User.builder().id("ssafy4").password("1233").name("ssafy").address("test").phone("1234").build();
			uRepo.insert(user);
			User result = uRepo.selectById("ssafy4");
			log.info("result {}", result);
			assertEquals(result, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
