package com.ssafy.myhome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.myhome.model.house.dto.DongCode;
import com.ssafy.myhome.model.house.dto.HouseDeal;
import com.ssafy.myhome.model.house.repo.DongCodeRepo;
import com.ssafy.myhome.model.house.repo.HouseDealRepo;
import com.ssafy.myhome.model.user.dto.User;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
public class MyhomeHouseTest {
	@Autowired
	HouseDealRepo hdRepo;
	
	@Autowired
	DongCodeRepo dcRepo;
	
	@Test
	public void selectByDongCodeTest() {
		DongCode result = dcRepo.selectByDongCode("1111010100");
		assertEquals(result.getDongName(), "청운동");
	}

	@Test
	public void selectByAptCodeTest() {
		BigInteger aptCode = new BigInteger("11110000000013");
		List<HouseDeal> result = hdRepo.selectByAptCode(aptCode);
		log.info("selectByAptCodeTest {}", result);
		//assertEquals(result.size(), 33);
	}
	
	
	@Test
	public void selectHouseDealByDongCodeTest() {
		List<HouseDeal> result = hdRepo.selectByDongCode("1111011700");
		log.info("selectHouseDealByDongCodeTest {}", result);
		//assertEquals(result.size(), 41);
	}
	
	@Test
	public void selectByDongCodeAndYearAndMonth() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dongCode", "1111011500");
		map.put("year", 2019);
		map.put("month", 7);
		List<HouseDeal> result = hdRepo.selectByDongCodeAndYearAndMonth(map);
		log.info("selectByDongCodeAndYearAndMonth {}", result);
		if (result != null)
			assertNotNull(result.get(0).getHouseInfo());
		
		assertEquals(result.size(), 3);
	}
	
	@Test
	public void selectByAptNameTest() {
		List<HouseDeal> result = hdRepo.selectByAptName("포레스트힐시티");
		log.info("selectByAptNameTest {}", result);
		assertEquals(result.size(), 5);
	}
	
	@Test
	public void selectCountAll() {
		int result = hdRepo.selectCountAll();
		log.info("count {}", result);
		// assertEquals(result, 68864);
	}
}
