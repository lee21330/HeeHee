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
public class AdminMainDTO {

	//전체 주문현황(일반/경매)

	//일반 주문건수 - 쿼리 AS 명칭
	private int sellProCtn;

	//일반 주문액 - 쿼리 AS 명칭
	private int sellProPriceSum;

	//경매 주문건수 - 쿼리 AS 명칭
	private int aucProCtn;

	//경매 주문액 - 쿼리 AS 명칭
	private int aucProPriceSum;

	//일반상품 주문현황

	//판매중
	private int sellIng;

	//예약중
	private int sellRes;

	//거래완료
	private int sellEnd;

	//판매중지
	private int sellStop;

	//경매상품 주문현황

	//입찰
	private int aucIng;

	//낙찰
	private int aucRes;

	//거래완료
	private int aucEnd;

	//판매중지
	private int aucStop;
}
