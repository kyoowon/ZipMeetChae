package com.ssafy.myhome.model.house.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myhome.model.house.dto.HouseDeal;
import com.ssafy.myhome.model.house.repo.HouseDealRepo;

@Service
public class HouseDealService {

	
	@Autowired
	HouseDealRepo hdRepo;
	
	public List<HouseDeal> searchByAptCode(BigInteger aptCode) {
		return  hdRepo.selectByAptCode(aptCode);
	}
	
	public List<HouseDeal> searchByDongCode(String dongCode) {
		return hdRepo.selectByDongCode(dongCode);
	}
	
	public List<HouseDeal> searchByDongCodeAndYM(String dongCode, int year, int month) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dongCode", dongCode);
		map.put("year", year);
		map.put("month", month);
		return  hdRepo.selectByDongCodeAndYearAndMonth(map);
	}
	
	
	
	public List<HouseDeal> searchByAptName(String apartmentName) {
		return hdRepo.selectByAptName(apartmentName);
	}
	
	public int countAll() {
		return hdRepo.selectCountAll();
	}
	
}
