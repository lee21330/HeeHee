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
public class AdminAuctionDTO {
	//등록번호 - AUC_PRODUCT
	private int product_seq;
	
	//카테고리 - PRODUCT_CATEGORY
	private String category;
	
	//세부 카테고리 - PRODUCT_CATEGORY
	private String detail_category;
	
	//판매자ID - AUC_PRODUCT
	private String seller_id;
	
	//제목 - AUC_PRODUCT
	private String auction_title;
	
	//경매 만료 시간 - AUC_PRODUCT
	private Date exp_time;
	
	//경매상태 - AUC_PRODUCT
	private String auc_status;
	
}
