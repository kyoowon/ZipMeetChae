package com.ssafy.myhome.model.house.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseInfo {

	private BigInteger aptCode;
	private int buildYear;
	private String dong;
	private String dongCode;
	private String apartmentName;
	private String jibun;
	private String lng;
	private String lat;
	private String img;

}
