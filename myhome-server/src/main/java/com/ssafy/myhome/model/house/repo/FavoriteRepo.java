package com.ssafy.myhome.model.house.repo;
import java.util.List;
import java.util.Map;

import com.ssafy.myhome.model.house.dto.Favorite;



public interface FavoriteRepo {
	
	// id로 관심매물(aptCode) select
	public List<Map<String, Object>> selectAptCodeById(String id);
	
	// 관심매물 추가
	public int insert(Favorite fav);
	
	// 관심매물 삭제
	public int delete(Favorite fav);
}
