package com.ssafy.myhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myhome.model.house.dto.DongCode;
import com.ssafy.myhome.model.house.dto.HouseDeal;
import com.ssafy.myhome.model.house.dto.HouseInfo;
import com.ssafy.myhome.model.house.service.DongCodeService;
import com.ssafy.myhome.model.house.service.HouseDealService;
import com.ssafy.myhome.model.house.service.HouseInfoService;

import io.swagger.annotations.ApiParam;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/aptinfos")
@CrossOrigin("*")
@Slf4j
public class AptInfoRestController {

	@Autowired
	HouseDealService hdService;

	@Autowired
	HouseInfoService hiService;

	@Autowired
	DongCodeService dcService;

	// 시도 호출
	@GetMapping("/sido")
	public ResponseEntity<List<Map<String, String>>> sido() throws Exception {
		log.info("sido - 호출");
		return new ResponseEntity<List<Map<String, String>>>(dcService.getSido(), HttpStatus.OK);
	}

	// 구군 호출
	@GetMapping("/gugun")
	public ResponseEntity<List<Map<String, String>>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		log.info("gugun - 호출");
		return new ResponseEntity<List<Map<String, String>>>(dcService.getGugunInSido(sido), HttpStatus.OK);
	}

	// 동 호출
	@GetMapping("/dong")
	public ResponseEntity<List<Map<String, String>>> dong(
			@RequestParam("gugun") @ApiParam(value = "구코드.", required = true) String gugun) throws Exception {
		return new ResponseEntity<List<Map<String, String>>>(dcService.getDongInGugun(gugun), HttpStatus.OK);
	}

	// 동 코드
	@GetMapping("/search")
	public ResponseEntity<List<HouseInfo>> searchByDongCode(String LAWDCD) {
		log.info("LAWD_CD {}", LAWDCD);
		List<HouseInfo> list = hiService.searchByDongCode(LAWDCD);
		log.info("searchdc list : {}", list);
		return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.OK);
	}

	// 동 코드 + 년월로 검색
	@GetMapping("/search/ywd")
	public ResponseEntity<List<HouseDeal>> searchByDongCodeAndYM(String LAWDCD, String DEALYMD) {

		log.info("LAWD_CD {} , DEAL_YMD {}", LAWDCD, DEALYMD);
		int year = Integer.parseInt(DEALYMD.substring(0, 4));
		int month = Integer.parseInt(DEALYMD.substring(4, 6));
		List<HouseDeal> list = hdService.searchByDongCodeAndYM(LAWDCD, year, month);
		log.info("searchdc list : {}", list);
		return new ResponseEntity<List<HouseDeal>>(list, HttpStatus.OK);
	}

	// 아파트 이름으로 검색
	@GetMapping("/search/name")
	public ResponseEntity<List<HouseDeal>> searchByAptName(String aptName) {
		log.info("aptName {}", aptName);
		List<HouseDeal> list = hdService.searchByAptName(aptName);
		log.info("list : {}", list);
		return new ResponseEntity<List<HouseDeal>>(list, HttpStatus.OK);
	}

	// 아파트 코드로 검색
	@GetMapping("/aptCode")
	public ResponseEntity<List<HouseDeal>> searchByAptCode(String aptCode) {
		BigInteger aptCode2 = new BigInteger(aptCode);
		List<HouseDeal> list = hdService.searchByAptCode(aptCode2);
		return new ResponseEntity<List<HouseDeal>>(list, HttpStatus.OK);
	}

	@GetMapping("/stats")
	public ResponseEntity<List<Map<String, Object>>> sidoStats() {
		List<Map<String, Object>> list = hiService.sidoStats();
		log.info("stats : {}", list);
		return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
	}
}
