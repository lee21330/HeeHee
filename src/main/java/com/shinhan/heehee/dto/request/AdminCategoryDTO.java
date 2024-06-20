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
public class AdminCategoryDTO {
	
	//번호 - PRODUCT_CATEGORY
	private int product_cate_seq;
	
	//카테고리 - PRODUCT_CATEGORY
	private String category;
	
	//세부 카테고리 - PRODUCT_CATEGORY
	private String detail_category;
	
	//작성자ID
	private String id;
	
	//작성일 - 테이블 미생성으로 결정 필요
	private Date create_date;
	
}
