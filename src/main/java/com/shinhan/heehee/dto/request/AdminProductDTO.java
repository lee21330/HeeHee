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
	
	//등록번호 - SELL_PRODUCT
	private int product_seq;
	
	//카테고리 - PRODUCT_CATEGORY
	private String category;
	
	//세부 카테고리 - PRODUCT_CATEGORY
	private String detail_category;
	
	//판매자ID - SELL_PRODUCT
	private String id;
	
	//제목 - SELL_PRODUCT
	private String article_title;
	
	//게시일 - SELL_PRODUCT
	private Date create_date;
	
	//상태 - SELL_PRODUCT
	private String pro_status;
	
	//정지사유 - SELL_PRODUCT
	private String product_ban_reason;
	
	//삭제용 - 파라미터 1개 생성자 추가
	public AdminProductDTO(int product_seq) {
		this.product_seq = product_seq;
	}
	
	//수정용 - 파라미터 3개 생성자 추가
	public AdminProductDTO(int product_seq, String pro_status, String product_ban_reason) {
		this.product_seq = product_seq;
		this.pro_status = pro_status;
		this.product_ban_reason = product_ban_reason;
	}
	
}
