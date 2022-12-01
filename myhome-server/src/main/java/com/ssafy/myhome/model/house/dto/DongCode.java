package com.ssafy.myhome.model.house.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DongCode {
	
	  private String dongCode;
	  private String sidoName;
	  private String gugunName;
	  private String dongName;
	  
}
