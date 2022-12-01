package com.ssafy.myhome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.myhome.model.house.dto.HouseInfo;
import com.ssafy.myhome.model.house.repo.HouseInfoRepo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
public class MyhomeHouseInfoTest {
	
	@Autowired
	HouseInfoRepo hiRepo;
	
	@Test
	public void selectByAptCodeTest() {
		BigInteger aptCode = new BigInteger("11110000000003");
		HouseInfo hi = hiRepo.selectByAptCode(aptCode);
		log.info("hi {}", hi);
		assertNotNull(hi);
	}
	@Test
	public void selectByDongCodeTest() {
		String dongCode = "1111011500";
		List<HouseInfo> hi = hiRepo.selectByDongCode(dongCode);
		log.info("list hi {}", hi);
		assertNotNull(hi);
	}
	
}
