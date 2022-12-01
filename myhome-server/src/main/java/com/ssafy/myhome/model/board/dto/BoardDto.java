package com.ssafy.myhome.model.board.dto;

import java.math.BigInteger;
import java.sql.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "BoardDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class BoardDto {
	@ApiModelProperty(value = "글번호")
	private int articleno;
	
	@ApiModelProperty(value = "작성자 아이디")
	private String userid;
	
	@ApiModelProperty(value = "글제목")
	private String subject;
	
	@ApiModelProperty(value = "글내용")
	private String content;
	
	@ApiModelProperty(value = "조회수")
	private int hit;
	
	@ApiModelProperty(value = "작성일")
	private String regtime;
	
	@ApiModelProperty(value = "아파트코드")
	private BigInteger aptCode;
	
	@ApiModelProperty(value = "층")
	private String floor;
	
	@ApiModelProperty(value = "아파트이름")
	private String apartmentName;
	
	@ApiModelProperty(value = "면")
	private String area;
	
	@ApiModelProperty(value = "금액")
	private String price;
	
	@ApiModelProperty(value = "시작일")
	private String startTime;
	
	@ApiModelProperty(value = "종료일")
	private String endTime;
	
	@ApiModelProperty(value = "거래상태")
	private int dealState;

}