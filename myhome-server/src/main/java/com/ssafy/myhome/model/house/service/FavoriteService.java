package com.ssafy.myhome.model.house.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myhome.model.house.dto.Favorite;
import com.ssafy.myhome.model.house.repo.FavoriteRepo;

@Service
public class FavoriteService {

	@Autowired
	FavoriteRepo fRepo;
	
	public List<Map<String, Object>> selectAptCodeById(String id){
		return fRepo.selectAptCodeById(id);
	}
	
	// 관심매물 추가
	public int insert(Favorite fav) {
		return fRepo.insert(fav);
	};
	
	public int delete(Favorite fav) {
		return fRepo.delete(fav);
	};
	
}
