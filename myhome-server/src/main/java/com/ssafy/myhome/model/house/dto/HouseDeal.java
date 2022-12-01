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
public class HouseDeal {

	  private BigInteger no;
	  private BigInteger aptCode;
	  private String dealAmount;
	  private int dealYear;
	  private int dealMonth;
	  private int dealDay;
	  private String area;
	  private String floor;
	  private String type;
	  private String rentMoney;
	  private HouseInfo houseInfo;
}
