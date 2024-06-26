package com.shinhan.heehee.dto.request;

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
	
	//삭제용 - 파라미터 1개 생성자 추가
	public AdminCategoryDTO(int product_cate_seq) {
		this.product_cate_seq = product_cate_seq;
	}
}
