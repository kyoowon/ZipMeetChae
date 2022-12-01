package com.ssafy.myhome.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myhome.model.house.dto.Favorite;
import com.ssafy.myhome.model.house.dto.HouseDeal;
import com.ssafy.myhome.model.house.dto.HouseInfo;
import com.ssafy.myhome.model.house.repo.FavoriteRepo;
import com.ssafy.myhome.model.house.service.FavoriteService;
import com.ssafy.myhome.model.house.service.HouseDealService;
import com.ssafy.myhome.model.house.service.HouseInfoService;
import com.ssafy.myhome.model.user.dto.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/favorite")
@CrossOrigin("*")
@Slf4j
@Api("관심지역 컨트롤러  API")
public class FavoriteRestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	FavoriteService favoriteService;

	// {id} 에 해당하는 관심매물 아파트를 가져온다.
	@GetMapping("/{id}")
	public ResponseEntity<List<Map<String, Object>>> selectAptCodeById(@PathVariable("id") String id) {
		List<Map<String, Object>> list = favoriteService.selectAptCodeById(id);
		log.info("list : {}", list);
		return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
	}

	// 해당 {id}와 {aptCode}로 관심매물을 추가한다.
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Favorite fav) {
		log.info("id {}, aptCode {}", fav);
		int result = 0;
		Map<String, Object> resultMap = new HashMap<>();
		try {
			result = favoriteService.insert(fav);
			if (result == 1) {
				resultMap.put("message", SUCCESS);
			} else if (result == 0) {
				resultMap.put("message", FAIL);
			}
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// {id}와 {aptCode}에 해당하는 관심매물을 삭제한다
	@DeleteMapping("/{id}/{strAptCode}")
	public ResponseEntity<?> delete(@PathVariable String id, @PathVariable String strAptCode) {
		int result = 0;
		BigInteger aptCode = new BigInteger(strAptCode);
		Map<String, Object> resultMap = new HashMap<>();
		Favorite fav = new Favorite(id, aptCode);
		try {
			result = favoriteService.delete(fav);
			if (result == 1) {
				resultMap.put("message", SUCCESS);
			} else if (result == 0) {
				resultMap.put("message", FAIL);
			}
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
