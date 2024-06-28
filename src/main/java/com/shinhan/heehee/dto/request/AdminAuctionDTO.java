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
	private int productSeq;
	
	//카테고리 - PRODUCT_CATEGORY
	private String category;
	
	//세부 카테고리 - PRODUCT_CATEGORY
	private String detailCategory;
	
	//판매자ID - AUC_PRODUCT
	private String sellerId;
	
	//제목 - AUC_PRODUCT
	private String auctionTitle;
	
	//경매 만료 시간 - AUC_PRODUCT
	private Date expTime;
	
	//경매상태 - AUC_PRODUCT
	private String aucStatus;
	
	//정지사유 - AUC_PRODUCT
	private String aucBanReason;
	
	//삭제 - 파라미터 1개 
	public AdminAuctionDTO (int productSeq) {
		this.productSeq = productSeq;
	}
	
	//수정용 - 파라미터 3개 생성자 추가
	public AdminAuctionDTO (int productSeq, String aucStatus, String aucBanReason) {
		this.productSeq = productSeq;
		this.aucStatus = aucStatus;
		this.aucBanReason = aucBanReason;
	}
	
}
