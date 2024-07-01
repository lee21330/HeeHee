package com.shinhan.heehee.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileDTO {
	private String profileImg;
	private String id;
	private String name;
	private String pw;
	private String email;
	private String phoneNum;
	private String nickName;
	private String bank;
	private String accountNum;
	private String userIntroduce;
	private String address;
	private Date modifyDate;
}
