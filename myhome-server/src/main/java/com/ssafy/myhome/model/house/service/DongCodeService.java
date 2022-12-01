package com.ssafy.myhome.model.house.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myhome.model.house.dto.DongCode;
import com.ssafy.myhome.model.house.repo.DongCodeRepo;

@Service
public class DongCodeService {

	@Autowired
	DongCodeRepo dcRepo;
	
	public DongCode searchByDongCode(String dongCode){
		return dcRepo.selectByDongCode(dongCode);
	}

	public List<Map<String, String>> getSido() {
		return dcRepo.getSido();
	}

	public List<Map<String, String>> getGugunInSido(String sido) {
		return dcRepo.getGugunInSido(sido);
	}

	public List<Map<String, String>> getDongInGugun(String gugun) {
		return dcRepo.getDongInGugun(gugun);
	}
	
}
