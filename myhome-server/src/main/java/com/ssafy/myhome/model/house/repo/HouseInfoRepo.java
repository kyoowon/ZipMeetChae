package com.ssafy.myhome.model.house.repo;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.ssafy.myhome.model.house.dto.HouseInfo;


public interface HouseInfoRepo {

	public HouseInfo selectByAptCode(BigInteger aptCode);

	public List<HouseInfo> selectByDongCode(String dongCode);

	public List<Map<String, Object>> sidoStats();
}
