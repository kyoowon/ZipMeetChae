package com.ssafy.myhome.model.house.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorite {
	
	private String id;
	private BigInteger aptCode;
	
}
