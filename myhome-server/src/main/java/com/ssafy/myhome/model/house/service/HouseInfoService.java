package com.ssafy.myhome.model.house.service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myhome.model.house.dto.HouseDeal;
import com.ssafy.myhome.model.house.dto.HouseInfo;
import com.ssafy.myhome.model.house.repo.HouseDealRepo;
import com.ssafy.myhome.model.house.repo.HouseInfoRepo;

@Service
public class HouseInfoService {
	
	@Autowired
	HouseInfoRepo hRepo;
	
	@Autowired
	HouseDealRepo hdRepo;
	
	public HouseInfo searchByAptCode(BigInteger aptCode) {
		return hRepo.selectByAptCode(aptCode);
	}
	
	public List<HouseInfo> searchByDongCode(String dongCode) {
		return hRepo.selectByDongCode(dongCode);
	}


	public List<Map<String, Object>> sidoStats() {
		return hRepo.sidoStats();
	}
	
}
