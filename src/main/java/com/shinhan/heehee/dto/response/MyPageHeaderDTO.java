package com.shinhan.heehee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyPageHeaderDTO {

	private String profileImg;
	private String id;
	private String nickName;
	private String userIntroduce;
	private int userRating;
	private String bank;
	private String accountNum;
	private int userPoint;
	
}
