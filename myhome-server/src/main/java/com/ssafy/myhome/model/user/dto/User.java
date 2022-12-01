package com.ssafy.myhome.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String token;
	private String joinDate;

}
