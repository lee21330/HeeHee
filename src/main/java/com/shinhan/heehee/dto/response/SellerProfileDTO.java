package com.shinhan.heehee.dto.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerProfileDTO {
	private String profileImg;
	private String nickName;
	private String userIntroduce;
	private int userRating;
	private Date createDate;
	private String id;
	private int createDiff;
	private int counting;
}
