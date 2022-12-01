package com.ssafy.myhome.model.house.repo;

import java.util.List;
import java.util.Map;

import com.ssafy.myhome.model.house.dto.DongCode;


public interface DongCodeRepo {

	public DongCode selectByDongCode(String dongCode);

	public List<Map<String, String>> getSido();

	public List<Map<String, String>> getGugunInSido(String sido);

	public List<Map<String, String>> getDongInGugun(String gugun);
	
}
