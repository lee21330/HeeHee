package com.shinhan.heehee.dto.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminUserDTO {
	
	//회원명 - HH_USER
	private String name;
	
	//아이디 - HH_USER
	private String id;
	
	//이메일 - HH_USER
	private String email;
	
	//전화번호 - HH_USER
	private String phoneNum;
	
	//주소 - HH_USER
	private String address;
	
	//가입일 - HH_USER
	private Date createDate;
	
	//페이징 처리를 위한 변수
	private int size;
	private int page;
}
