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
	private int productSeq;
	
	//카테고리 - PRODUCT_CATEGORY
	private String category;
	
	//세부 카테고리 - PRODUCT_CATEGORY
	private String detailCategory;
	
	//판매자ID - SELL_PRODUCT
	private String id;
	
	//제목 - SELL_PRODUCT
	private String articleTitle;
	
	//게시일 - SELL_PRODUCT
	private Date createDate;
	
	//상태 - SELL_PRODUCT
	private String proStatus;
	
	//정지사유 - SELL_PRODUCT
	private String productBanReason;
	
	//구매자ID - DEAL_HISTORY : 메인 페이지용
	private String buyerId;
	
	//거래금액 - SELL_PRODUCT : 메인 페이지용
	private int productPrice;
	
	//페이징 처리를 위한 변수
	private int size;
	private int page;
	
	//삭제용 - 파라미터 1개 생성자 추가
	public AdminProductDTO(int productSeq) {
		this.productSeq = productSeq;
	}
	
	//수정용 - 파라미터 3개 생성자 추가
	public AdminProductDTO(int productSeq, String proStatus, String productBanReason) {
		this.productSeq = productSeq;
		this.proStatus = proStatus;
		this.productBanReason = productBanReason;
	}
	
}
