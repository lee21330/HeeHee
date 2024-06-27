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
	
	//작성자ID - PRODUCT_CATEGORY
	private String id;
	
	//작성일 - PRODUCT_CATEGORY
	private Date create_date;
	
	//삭제용 - 파라미터 1개 생성자 추가
	public AdminCategoryDTO(int product_cate_seq) {
		this.product_cate_seq = product_cate_seq;
	}
	
	//신규등록용 - 파라미터 2개 생성자 추가
	public AdminCategoryDTO(String category, String detail_category) {
		this.category = category;
		this.detail_category = detail_category;
	}
	
	//수정용 - 파라미터 3개 생성자 추가
	public AdminCategoryDTO(int product_cate_seq, String category, String detail_category) {
		this.product_cate_seq = product_cate_seq;
		this.category = category;
		this.detail_category = detail_category;
	}
}
