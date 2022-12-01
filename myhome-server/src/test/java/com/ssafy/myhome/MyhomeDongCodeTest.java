package com.ssafy.myhome;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.myhome.model.house.dto.DongCode;
import com.ssafy.myhome.model.house.dto.HouseInfo;
import com.ssafy.myhome.model.house.repo.DongCodeRepo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
public class MyhomeDongCodeTest {
	@Autowired
	DongCodeRepo dcRepo;
	
	@Test
	public void getSido() {
		List<Map<String, String>> sido = dcRepo.getSido();
		log.info("list sido {}", sido);
		//assertNotNull(hi);
	}
	
	@Test
	public void getGugunInSido() {
		String sido = "서울특별시";
		List<Map<String, String>> gugun = dcRepo.getGugunInSido(sido);
		log.info("list gugun {}", gugun);
	}
	
	@Test
	public void getDongInGugun() {
		String gugun = "중구";
		List<Map<String, String>> dong = dcRepo.getDongInGugun(gugun);
		log.info("list gugun {}", dong);
	}
}
