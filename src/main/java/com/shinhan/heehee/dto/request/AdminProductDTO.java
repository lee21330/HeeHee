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
public class AdminProductDTO {
	//등록번호
	private int product_seq;
	
	//카테고리
	private String category;
	
	//세부 카테고리
	private String detail_category;
	
	//판매자ID
	private String id;
	
	//제목
	private String article_title;
	
	//게시일
	private Date create_date;
	
	//상태
	private String pro_status;
	
}
