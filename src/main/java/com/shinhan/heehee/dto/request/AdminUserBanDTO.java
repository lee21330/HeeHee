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
	
	//회원명 
	private String name;
	
	//아이디 
	private String id;
	
	//정지내용 
	private String banContent;
	
	//시작일 
	private Date banStr;
	
	//시작일 String
	private String banStrSt;
	
	//종료일 
	private Date banEnd;
	
	//페이징 처리를 위한 변수
	private int size;
	private int page;
	
	//삭제용 - 파라미터 2개 변수
	public AdminUserBanDTO(String id, String banStrSt) {
		this.id = id;
		this.banStrSt = banStrSt;
	}
	
	//신규추가/수정용 - 파라미터 4개 변수
	public AdminUserBanDTO(String id, String banContent, Date banStr, Date banEnd) {
		this.id = id;
		this.banContent = banContent;
		this.banStr = banStr;
		this.banEnd = banEnd;
	}
}
