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
public class AdminUserBanDTO {
	//상태 
	private String status;
	
	//회원명 
	private String name;
	
	//아이디 
	private String id;
	
	//정지내용 
	private String banContent;
	
	//시작일 
	private Date banStr;
	
	//종료일 
	private Date banEnd;
}
