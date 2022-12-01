package com.ssafy.myhome.model.house.repo;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.myhome.model.house.dto.HouseDeal;


public interface HouseDealRepo {
	
	public List<HouseDeal> selectByAptCode(BigInteger aptCode);
	
	public List<HouseDeal> selectByDongCode(String dongCode);
	
	public List<HouseDeal> selectByDongCodeAndYearAndMonth(HashMap<String, Object> map);
	
	public List<HouseDeal> selectByAptName(String apartmentName);
	
	public int selectCountAll();

	
}
