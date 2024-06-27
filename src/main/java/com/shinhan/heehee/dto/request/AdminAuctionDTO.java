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
	
	//정지사유 - AUC_PRODUCT
	private String auc_ban_reason;
	
	//삭제 - 파라미터 1개 
	public AdminAuctionDTO (int product_seq) {
		this.product_seq = product_seq;
	}
	
	//수정용 - 파라미터 3개 생성자 추가
	public AdminAuctionDTO (int product_seq, String auc_status, String auc_ban_reason) {
		this.product_seq = product_seq;
		this.auc_status = auc_status;
		this.auc_ban_reason = auc_ban_reason;
	}
	
}
